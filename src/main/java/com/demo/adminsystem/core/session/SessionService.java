package com.demo.adminsystem.core.session;

import com.demo.adminsystem.core.entity.TbSystemUser;
import com.demo.adminsystem.core.util.PwdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:29
 * @version: V1.0
 * @detail:
 **/
@Service
public class SessionService {
    @Resource
    HttpSession session;

    public final static String SESSION_USER = "user";
    public final static String SESSION_TOKEN = "X-Token";


    public void setUser(TbSystemUser user) {
        if (user == null) {
            return;
        }
        session.setAttribute(SESSION_USER, user);
        String token = PwdUtil.aesEncrypt(user.getName() + "." + user.getPassword());
        session.setAttribute(SESSION_TOKEN, token);
    }

    public TbSystemUser getUser() {
        Object user = session.getAttribute(SESSION_USER);
        if (user == null) {
            return null;
        }
        return (TbSystemUser) user;
    }

    public void flush() {
    }

    public void exit() {
        session.invalidate();
    }

    public String getToken() {
        Object token = session.getAttribute(SESSION_TOKEN);
        if (token != null) {
            return token.toString();
        }
        return null;
    }
}
