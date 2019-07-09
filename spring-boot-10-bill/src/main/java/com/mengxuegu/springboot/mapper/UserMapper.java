package com.mengxuegu.springboot.mapper;


import com.mengxuegu.springboot.entities.User;

import java.util.List;


public interface UserMapper {
    List<User> getUsers(User user);
    User getUserByName(String username);
    User getUserById(Integer id);
    int addUser(User user);
    int deleteUserById(Integer id);
    int updateUser(User user);


}
