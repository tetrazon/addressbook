package com.smuniov.addressbook.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.smuniov.addressbook.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {
    @JsonView(Views.UsernameRole.class)
    private RoleName roleName;
    //private String roleName;
}
