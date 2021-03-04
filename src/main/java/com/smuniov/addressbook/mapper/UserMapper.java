package com.smuniov.addressbook.mapper;

import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    UserDto userToUserDto (User user);
    User userDtoToUser (UserDto userDto);
    List<User> userDtosToUsers (List<UserDto> userDtos);
    List<UserDto> usersToUserDtos (List<User>users);
}
