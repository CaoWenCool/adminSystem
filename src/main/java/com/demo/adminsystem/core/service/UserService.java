package com.demo.adminsystem.core.service;

import com.demo.adminsystem.core.entity.User;

import java.util.List;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:47
 * @version: V1.0
 * @detail:
 **/
public interface UserService {

    User getUser(Integer id);

    List<User> getUserList(String keyword);

    void addUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id);
}
