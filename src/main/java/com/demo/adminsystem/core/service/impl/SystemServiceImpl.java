package com.demo.adminsystem.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.demo.adminsystem.core.dao.TbSystemUserDao;
import com.demo.adminsystem.core.entity.TbSystemUser;
import com.demo.adminsystem.core.response.UserInfo;
import com.demo.adminsystem.core.service.SystemService;
import com.demo.adminsystem.core.session.SessionService;
import com.demo.adminsystem.core.util.CommonResult;
import com.demo.adminsystem.core.util.PwdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:48
 * @version: V1.0
 * @detail:
 **/
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class})
@Service
public class SystemServiceImpl implements SystemService {
    @Resource
    TbSystemUserDao userDao;
    @Resource
    SessionService sessionService;

    @Override
    public CommonResult login(String username, String password) {
        TbSystemUser user = userDao.createLambdaQuery().andEq(TbSystemUser::getName, username).single();
        if (user == null) {
            return CommonResult.resultFail("用户不存在");
        }
        if (user.getInUse() != 1) {
            return CommonResult.resultFail("用户被冻结");
        }
        if (!user.getPassword().equals(PwdUtil.aesEncrypt(password))) {
            return CommonResult.resultFail("密码错误");
        }

        sessionService.setUser(user);
        JSONObject body = new JSONObject();
        body.put("token", sessionService.getToken());
        return CommonResult.resultSuccess("登陆成功", body);
    }

    @Override
    public CommonResult exit() {
        sessionService.exit();
        return CommonResult.resultSuccess("用户已退出出登录");
    }

    @Override
    public CommonResult sendForgetPassWordEmail(String username) {
        return null;
    }

    @Override
    public CommonResult resetPassWord(String username, String newPassword) {
        return null;
    }

    @Override
    public CommonResult getUserInfo() {
        UserInfo info = new UserInfo();
        info.setUser(sessionService.getUser());
        info.setToken(sessionService.getToken());
        return CommonResult.resultSuccess("success", info);
    }
}
