package com.demo.adminsystem.core.service;

import com.demo.adminsystem.core.entity.TbSystemUser;

import java.util.List;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:47
 * @version: V1.0
 * @detail:
 **/
public interface UserService {


    TbSystemUser getUser(Integer id);

    List<TbSystemUser> getUserList(String keyword);

    void addUser(TbSystemUser user);

    TbSystemUser updateUser(TbSystemUser user);

    void deleteUser(Integer id);
}
