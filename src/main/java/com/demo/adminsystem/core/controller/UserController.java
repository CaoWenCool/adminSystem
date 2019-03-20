package com.demo.adminsystem.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.adminsystem.core.annotations.OperationLog;
import com.demo.adminsystem.core.dao.TbSystemRoleDao;
import com.demo.adminsystem.core.entity.TbSystemRole;
import com.demo.adminsystem.core.entity.TbSystemUser;
import com.demo.adminsystem.core.service.UserService;
import com.demo.adminsystem.core.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:47
 * @version: V1.0
 * @detail:
 **/
@Api(tags = {"用户操作相关接口"})
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private TbSystemRoleDao roleDao;


    @ApiOperation(value = "获取用户信息", notes = "获取用户详细信息")
    @GetMapping("/info")
    @OperationLog(module = "用户设置", operation = "获取用户信息", remark = "")
    public CommonResult userInfo(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer id) {
        return CommonResult.resultSuccess("success", userService.getUser(id));
    }

    @ApiOperation(value = "获取用户列表", notes = "根据用户条件查询")
    @GetMapping("/list")
    @OperationLog(module = "用户设置", operation = "获取用户列表", remark = "")
    public CommonResult userList(
            @ApiParam(value = "姓名模糊查询") @RequestParam(required = false) String keyword) {
        List<TbSystemUser> list = userService.getUserList(keyword);
        List<JSONObject> userList = new ArrayList<>();
        for (TbSystemUser item : list) {
            JSONObject user = JSONObject.parseObject(JSONObject.toJSONString(item));
            user.remove("password");
            userList.add(user);
        }
        return CommonResult.resultSuccess(userList, userList.size());
    }

    @ApiOperation(value = "新增用户", notes = "")
    @PostMapping("/add")
    @OperationLog(module = "用户设置", operation = "新增用户", remark = "")
    public CommonResult addUser(
            @ApiParam(name = "user", value = "新增用户") @RequestParam TbSystemUser user) {
        userService.addUser(user);
        return CommonResult.resultSuccess("新增成功");
    }

    @ApiOperation(value = "更新用户", notes = "ID不能为空")
    @PutMapping("/update")
    @OperationLog(module = "用户设置", operation = "更新用户", remark = "")
    public CommonResult updateUser(
            @ApiParam(name = "user", value = "更新用户") TbSystemUser user) {
        userService.updateUser(user);
        return CommonResult.resultSuccess("修改成功");
    }

    @ApiOperation(value = "删除用户", notes = "ID不能为空")
    @DeleteMapping("/delete")
    @OperationLog(module = "用户设置", operation = "删除用户", remark = "")
    public CommonResult deleteUser(
            @ApiParam(name = "id", value = "用户ID") @RequestParam Integer id) {
        userService.deleteUser(id);
        return CommonResult.resultSuccess("删除成功");
    }

    @ApiOperation(value = "新增用户组", notes = "")
    @PostMapping("/role/add")
    @OperationLog(module = "用户设置", operation = "新增用户", remark = "")
    public CommonResult addRole(
            @ApiParam(name = "role", value = "新增用户分组") @RequestParam TbSystemRole role) {
        roleDao.insert(role);
        return CommonResult.resultSuccess("新增成功");
    }

    @ApiOperation(value = "获取用户分组列表", notes = "返回所有数据")
    @GetMapping("/role/list")
    @OperationLog(module = "用户设置", operation = "获取用户分组列表", remark = "")
    public CommonResult roleList(
            @ApiParam(value = "分组名称查询") @RequestParam(required = false) String keyword) {
        List<TbSystemRole> list;
        if (StringUtils.isNotEmpty(keyword)) {
            list = sqlManager.lambdaQuery(TbSystemRole.class).andLike(TbSystemRole::getName, keyword + "%").select();
        } else {
            list = roleDao.all();
        }
        return CommonResult.resultSuccess(list, list.size());
    }

    @ApiOperation(value = "删除角色", notes = "ID不能为空")
    @DeleteMapping("/role/delete")
    @OperationLog(module = "用户设置", operation = "删除角色", remark = "")
    public CommonResult roleUser(
            @ApiParam(name = "id", value = "用户ID") @RequestParam Integer id) {
        roleDao.deleteById(id);
        return CommonResult.resultSuccess("删除成功");
    }
}


