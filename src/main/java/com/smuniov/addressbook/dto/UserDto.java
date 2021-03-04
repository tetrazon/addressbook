package com.smuniov.addressbook.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.smuniov.addressbook.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank
    @NotNull
    @JsonView(Views.Username.class)
    private String username;

    @NotBlank
    @NotNull
    @JsonView(Views.UsernameRolePassword.class)
    private String password;

    @JsonView(Views.UsernameRole.class)
    private Set<RoleDto> roles;
}
