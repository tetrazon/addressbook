package com.smuniov.addressbook.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.dto.Views;
import com.smuniov.addressbook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/address-book/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UserDto userDto) throws URISyntaxException {
        System.out.println(userDto.getUsername());
        System.out.println(userDto.getPassword());
        userService.create(userDto);
        return ResponseEntity.created(new URI("/address-book/users/" + userDto.getUsername())).build();
    }

    @GetMapping
    @JsonView({Views.UsernameRole.class})
    public List<UserDto> personList(){
        return userService.getAll();
    }

    @GetMapping("/user/{username}")
    @JsonView({Views.UsernameRole.class})
    public UserDto getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @DeleteMapping("/user/{username}")
    public void deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
    }



}
