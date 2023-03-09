package com.belhard.bookstore.service.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleDto roleDto;

    private boolean isActive;
}
