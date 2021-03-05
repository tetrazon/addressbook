package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.entity.User;
import com.smuniov.addressbook.exceptions.BadDataException;
import com.smuniov.addressbook.mapper.UserMapper;
import com.smuniov.addressbook.repository.UserRepository;
import com.smuniov.addressbook.service.RoleService;
import com.smuniov.addressbook.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtos = userMapper.usersToUserDtos(userList);
        return userDtos;
    }

    @Override
    @Transactional
    public void create(UserDto userDto) {
        @NotBlank @NotNull String username = userDto.getUsername();
        if (userRepository.findByUsername(username) != null) {
            throw new BadDataException("user with username '" + username + "' is already existed");
        }
        User user = userMapper.userDtoToUser(userDto);
        roleService.setRoles(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto getByUsername(String username) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        return userMapper.userToUserDto(optionalUser.orElseThrow(() -> new BadDataException("user not found!")));
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new BadDataException("no user with username: " + username);
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usernameInContext = userDetails.getUsername();
        System.out.println("usernameInContext: " + usernameInContext);
        if (usernameInContext.equals(username)) {
            throw new BadDataException("cannot delete yourself!");
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public void update(UserDto userDto) {
        String username = userDto.getUsername();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BadDataException("no user with username: " + username);
        }
        roleService.setRoles(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}
