package com.emporio.auth.application.service;

import java.util.HashSet;
import java.util.Set;

import com.emporio.auth.api.request.UserRequestDto;
import com.emporio.auth.application.dto.UserDto;
import com.emporio.auth.infrastructure.entity.ERole;
import com.emporio.auth.infrastructure.entity.Role;
import com.emporio.auth.infrastructure.entity.User;
import com.emporio.auth.infrastructure.mapper.UserMapper;
import com.emporio.auth.infrastructure.repo.RoleRepository;
import com.emporio.auth.infrastructure.repo.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto createUser(UserRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ServiceException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ServiceException("Error: Email is already in use!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);
        user.setRoles(loadRoles(dto));

        return userMapper.toDto(userRepository.save(user));
    }

    private Set<Role> loadRoles(UserRequestDto userRequestDto) {
        Set<Role> roles = new HashSet<>();
        userRequestDto.getRoles().forEach(role -> {
            Role userRole = roleRepository.findByName(ERole.valueOf(role))
                    .orElseThrow(() -> new ServiceException("Error: Role is not found."));
            roles.add(userRole);
        });
        return roles;
    }

}
