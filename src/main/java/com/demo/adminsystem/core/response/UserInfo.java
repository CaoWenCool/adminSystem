package com.demo.adminsystem.core.response;

import com.alibaba.fastjson.JSONObject;
import com.demo.adminsystem.core.entity.TbSystemUser;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:51
 * @version: V1.0
 * @detail:
 **/
public class UserInfo {
    private JSONObject user;
    private String token;
    private JSONObject permission;

    public JSONObject getUser() {
        return user;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getPermission() {
        return permission;
    }

    public void setPermission(JSONObject permission) {
        this.permission = permission;
    }

    public void setUser(TbSystemUser user) {
        if (user == null) {
            return;
        }
        JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(user));
        data.remove("password");
        setUser(data);
    }
}
