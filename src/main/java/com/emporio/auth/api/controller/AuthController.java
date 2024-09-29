package com.emporio.auth.api.controller;


import com.emporio.auth.api.mapper.UserApiMapper;
import com.emporio.auth.api.request.AuthenticationRequestDto;
import com.emporio.auth.api.request.UserRequestDto;
import com.emporio.auth.api.response.AuthenticationResponseDto;
import com.emporio.auth.api.response.UserResponseDto;
import com.emporio.auth.application.service.UserService;
import com.emporio.auth.utility.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserApiMapper userApiMapper;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> createToken(@RequestBody AuthenticationRequestDto request) {
        log.info("createToken(-)");
        // Authenticate the user
        userDetailsService.loadUserByUsername(request.getUsername());

        // Generate the token
        String jwtToken = jwtUtil.generateToken(request.getUsername());

        return ResponseEntity.ok(new AuthenticationResponseDto(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userApiMapper.toResponseDto(userService.createUser(userRequestDto)));
    }
}


