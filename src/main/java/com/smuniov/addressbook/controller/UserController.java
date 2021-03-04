package com.smuniov.addressbook.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.dto.Views;
import com.smuniov.addressbook.service.UserService;
import org.springframework.http.ResponseEntity;
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
        userService.create(userDto);
        return ResponseEntity.created(new URI("/address-book/users/" + userDto.getUsername())).build();
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok("user updated");
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
