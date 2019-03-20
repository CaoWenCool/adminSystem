package com.demo.adminsystem.core.service;

import com.demo.adminsystem.core.util.CommonResult;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:47
 * @version: V1.0
 * @detail:  SystemService 系统接口
 **/
public interface SystemService {
    CommonResult login(String username, String password);

    CommonResult exit();

    CommonResult sendForgetPassWordEmail(String username);

    CommonResult resetPassWord(String username, String newPassword);

    CommonResult getUserInfo();

}
