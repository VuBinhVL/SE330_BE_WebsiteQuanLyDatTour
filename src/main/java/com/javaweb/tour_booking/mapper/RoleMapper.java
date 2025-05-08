package com.javaweb.tour_booking.mapper;

import com.javaweb.tour_booking.dto.RoleDTO;
import com.javaweb.tour_booking.entity.Role;

public class RoleMapper {
    public static RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
    public static Role mapToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
