package com.demo.adminsystem.core.service.impl;

import com.demo.adminsystem.core.exception.PlatformRuntimeException;
import com.demo.adminsystem.core.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.Query;
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
@Transactional(propagation = Propagation.REQUIRED,rollbackFor={Exception.class, RuntimeException.class})
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private SQLManager sqlManager;
    @Autowired
    private UserDao userDao;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(cacheNames = "user",key = "#id")
    @Override
    public User getUser(Integer id) {
        User user = sqlManager.single(User.class,id);
        return user;
    }

    @Override
    public List<User> getUserList(String keyword) {
        Query<User> query = sqlManager.query(User.class);
        List<User> list = query.andLike("name","%"+keyword+"%").select();
        return list;
    }

    @Override
    public void addUser(User user) {
        if(user == null){
            throw new PlatformRuntimeException("用户不能为空");
        }
        if(user.getPassword() == null){
            throw new PlatformRuntimeException("姓名不能为空");
        }
        user.setCreateTime(new Date());
        userDao.insert(user);
    }
    @CachePut(cacheNames = "user",key = "#user.id")
    @Override
    public User updateUser(User user) {
        if(user == null){
            throw new PlatformRuntimeException("用户不能为空");
        }
        if(user.getName() == null){
            throw  new PlatformRuntimeException("姓名不能为空");
        }
        if(user.getId() ==  null){
            throw new PlatformRuntimeException("ID不能为空");
        }
        User dbUser = userDao.single(user.getId());
        if(dbUser == null){
            throw  new PlatformRuntimeException("查询不到该用户");
        }
        dbUser.setName(user.getName());

        userDao.updateById(dbUser);
        return dbUser;
    }

    @CacheEvict(cacheNames = "user",key = "#id")
    @Override
    public void deleteUser(Integer id) {
        if(id == null){
            throw new PlatformRuntimeException("ID不能为空");
        }
        User dbUser = userDao.single(id);
        if(dbUser ==  null){
            throw new PlatformRuntimeException("查询不到该用户");
        }
        userDao.deleteById(id);
    }

    public void autoTask(Integer id){
        if(id ==null){
            throw  new PlatformRuntimeException("ID 不能为空");
        }
        User dbUser  = userDao.single(id);
        if(dbUser == null){
            throw new PlatformRuntimeException("查询不到该用户");
        }
        userDao.deleteById(id);
    }
}
