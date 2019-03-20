package com.demo.adminsystem.core.service.impl;

import com.demo.adminsystem.core.dao.TbSystemUserDao;
import com.demo.adminsystem.core.entity.TbSystemUser;
import com.demo.adminsystem.core.exception.PlatformRuntimeException;
import com.demo.adminsystem.core.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:47
 * @version: V1.0
 * @detail:
 **/
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, RuntimeException.class})
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private TbSystemUserDao userDao;

    String lockPath = "/lock/order";

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public TbSystemUser getUser(Integer id) {
        return userDao.single(id);
    }

    @Override
    public List<TbSystemUser> getUserList(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return userDao.all();
        }
        return userDao.createLambdaQuery().andLike(TbSystemUser::getName, keyword + "%").select();
    }

    @Override
    public void addUser(TbSystemUser user) {
        userDao.insert(user);
    }

    @Override
    public TbSystemUser updateUser(TbSystemUser user) {
        userDao.updateById(user);
        return userDao.single(user.getId());
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }
}
