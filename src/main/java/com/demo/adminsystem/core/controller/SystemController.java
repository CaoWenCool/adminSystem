package com.demo.adminsystem.core.controller;

import com.demo.adminsystem.core.annotations.NoLogin;
import com.demo.adminsystem.core.annotations.OperationLog;
import com.demo.adminsystem.core.controller.params.LoginUser;
import com.demo.adminsystem.core.entity.TbSystemLogs;
import com.demo.adminsystem.core.service.SystemService;
import com.demo.adminsystem.core.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 19:02
 * @version: V1.0
 * @detail:
 **/
public class SystemController {
    @Resource
    SystemService systemService;
    @Resource
    SQLManager sqlManager;

    @ApiOperation(value = "用户登录", notes = "password 加密")
    @PostMapping("/login")
    @NoLogin
    @OperationLog(module = "系统设置", operation = "用户登录", remark = "")
    public CommonResult login(@ApiParam(name = "user", value = "登陆信息") @RequestBody LoginUser user) {
        return systemService.login(user.getUsername(), user.getPassword());
    }

    @ApiOperation(value = "退出系统", notes = "清除session")
    @GetMapping("/exit")
    @OperationLog(module = "系统设置", operation = "用户退出", remark = "")
    public CommonResult exit() {
        return systemService.exit();
    }

    @ApiOperation(value = "获取用户权限", notes = "返回用户信息、用户菜单权限、单点权限、表单权限")
    @GetMapping("/userInfo")
    @NoLogin
    @OperationLog(module = "系统设置", operation = "获取用户信息", remark = "自动获取")
    public CommonResult getUserPermission() {
        return systemService.getUserInfo();
    }


    @ApiOperation(value = "获取用户日志", notes = "搜索所有系统日志信息")
    @GetMapping("/logs")
    @NoLogin
    @OperationLog(module = "系统设置", operation = "获取系统日志")
    public CommonResult getSystemLogs(@ApiParam(name = "keyword", value = "搜索信息") String keyword,
                                      @ApiParam(name = "pageSize", value = "每页最大数") Integer pageSize,
                                      @ApiParam(name = "pageIndex", value = "当前页") Integer pageIndex) {


        PageQuery<TbSystemLogs> query = new PageQuery<>();
        query.setPageSize(pageSize);
        query.setPageNumber(pageIndex);
        if (StringUtils.isNotEmpty(keyword)) {
            query.setPara("keyword", "%" + keyword + "%");

        }
        query.setOrderBy(" request_time DESC");

        sqlManager.pageQuery("tbSystemLogs.pageQuery", TbSystemLogs.class ,query);

        return CommonResult.resultSuccess(query.getList(), (int)query.getTotalRow());
    }
}
