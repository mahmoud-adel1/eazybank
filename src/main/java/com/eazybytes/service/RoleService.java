package com.eazybytes.service;

import com.eazybytes.enums.RoleEnum;
import com.eazybytes.model.Role;
import com.eazybytes.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getByRoleEnum(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum.name());
    }
}
