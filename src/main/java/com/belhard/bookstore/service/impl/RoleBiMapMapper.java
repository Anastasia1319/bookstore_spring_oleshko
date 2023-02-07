package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.dto.RoleDto;
import com.google.common.collect.EnumBiMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RoleBiMapMapper {
    private final EnumBiMap<Role, RoleDto> roleEnumBiMap = EnumBiMap.create(Role.class, RoleDto.class);

    public RoleBiMapMapper() {
        roleEnumBiMap.put(Role.ADMIN, RoleDto.ADMIN);
        roleEnumBiMap.put(Role.MANAGER, RoleDto.MANAGER);
        roleEnumBiMap.put(Role.CUSTOMER, RoleDto.CUSTOMER);
    }

    public RoleDto toRoleDto (Role role) {
        log.info("Transform from Role to RoleDto");
        return roleEnumBiMap.get(role);
    }

    public Role toRole (RoleDto roleDto) {
        log.info("Transform from RoleDto to Role");
        return roleEnumBiMap.inverse().get(roleDto);
    }
}
