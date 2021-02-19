package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.UserDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAll();
    void create(UserDto userDto);
    UserDto getByUsername(String username);

    @Secured("ADMIN")
    void deleteByUsername(String username);

    void update(UserDto userDtoUpdate);
}
