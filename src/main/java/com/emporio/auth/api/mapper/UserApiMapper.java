package com.emporio.auth.api.mapper;

import com.emporio.auth.api.response.UserResponseDto;
import com.emporio.auth.application.dto.UserDto;
import com.emporio.auth.infrastructure.entity.ERole;
import com.emporio.auth.infrastructure.entity.Role;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserApiMapper {
    UserDto toDto(UserResponseDto userResponseDto);

    @Mapping(target = "roleNames", expression = "java(rolesToRoleNames(userDto.getRoles()))")
    UserResponseDto toResponseDto(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDto partialUpdate(UserResponseDto userResponseDto, @MappingTarget UserDto userDto);

    default Set<ERole> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}