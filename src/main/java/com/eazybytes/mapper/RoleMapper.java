package com.eazybytes.mapper;

import com.eazybytes.dto.response.RoleResponseDto;
import com.eazybytes.model.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleMapper {
    public static RoleResponseDto mapToResponseDto(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setName(role.getName());
        return roleResponseDto;
    }

    public static Set<RoleResponseDto> mapToRoleResponseDtoSet(Set<Role> roles) {
        Set<RoleResponseDto> roleResponseDtoSet = new HashSet<>();
        for (Role role : roles) {
            roleResponseDtoSet.add(mapToResponseDto(role));
        }
        return roleResponseDtoSet;
    }
}
