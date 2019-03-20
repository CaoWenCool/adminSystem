package com.demo.adminsystem.core.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.demo.adminsystem.core.annotations.NoLogin;
import com.demo.adminsystem.core.annotations.OperationLog;
import com.demo.adminsystem.core.dao.TbSystemLogsDao;
import com.demo.adminsystem.core.entity.TbSystemLogs;
import com.demo.adminsystem.core.entity.TbSystemUser;
import com.demo.adminsystem.core.session.SessionService;
import com.demo.adminsystem.core.util.CommonResult;
import com.demo.adminsystem.core.util.PwdUtil;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:27
 * @version: V1.0
 * @detail: AuthorizedService 权限认证服务
 **/
public class AuthorizedService {
    static final Logger LOGGER = LogManager.getLogger(AuthorizedService.class);

    @Resource
    HttpServletRequest request;
    @Resource
    HttpServletResponse response;
    @Resource
    SessionService sessionService;
    @Resource
    TbSystemLogsDao logsDao;

    final static String PREFIX = "/api/v1";
    final static String PUT = "PUT";
    final static String POST = "POST";

    private String[] urls = new String[]{"/v2/api-docs"};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void request() {

    }


    @Around("request()")
    public Object beforeValidate(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info(request.getRequestURI() + ", " + request.getRemoteAddr());
        if (!request.getRequestURI().startsWith(PREFIX)) {
            return joinPoint.proceed();
        }
        if (noLogin(joinPoint) || validateCookie()) {
            Object key = saveSystemLog(joinPoint);
            Object data = joinPoint.proceed();
            if (key != null) {
                completeSystemLog(key, data);
            }
            return data;
        }
        response.setStatus(401);
        return CommonResult.build(401, "请先登录", 0);
    }

    boolean noLogin(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.isAnnotationPresent(NoLogin.class) || method.getDeclaringClass().isAnnotationPresent(NoLogin.class);
    }

    /**
     * 验证cookie
     */
    boolean validateCookie() {
        if (sessionService.getToken() != null && sessionService.getToken().equals(request.getHeader(SessionService.SESSION_TOKEN))) {
            return true;
        }
        return false;
    }

    /**
     * 收集系统日志
     *
     * @param joinPoint
     */
    @Async
    public Object saveSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (!method.isAnnotationPresent(OperationLog.class)) {
            return null;
        }
        TbSystemLogs systemLogs = new TbSystemLogs();


        OperationLog operationLog = method.getDeclaredAnnotation(OperationLog.class);
        systemLogs.setModule(operationLog.module());
        systemLogs.setOperation(operationLog.operation());
        systemLogs.setRemark(operationLog.remark());
        boolean isAES = operationLog.isAES();
        systemLogs.setIsAes(isAES ? 1 : 0);

        systemLogs.setRemoteIp(request.getRemoteAddr());
        systemLogs.setUrl(request.getRequestURI());
        systemLogs.setRequestTime(new Date());
        systemLogs.setRequestId(request.getRequestedSessionId());
        systemLogs.setRequestParams(JSONObject.toJSONString(request.getParameterMap()));

        String methodType = request.getMethod().toUpperCase();
        systemLogs.setRequestMethod(methodType);
        if (PUT.equals(methodType) || POST.equals(methodType)) {
            String body = "";
            try {
                body = IOUtils.toString(request.getInputStream(), "UTF-8");
                if (isAES) {
                    body = PwdUtil.aesEncrypt(body);
                }
            } catch (Exception e) {
                body = e.toString();
            }
            systemLogs.setRequestBody(body);

        }

        Object key = logsDao.insertReturnKey(systemLogs).getKey();
        LOGGER.info("The log Key is : " + key);
        return key;
    }

    @Async
    public void completeSystemLog(Object key, Object data) {
        TbSystemLogs systemLogs = logsDao.createLambdaQuery().andEq(TbSystemLogs::getId, key).single();
        TbSystemUser user = sessionService.getUser();
        if (systemLogs != null && user != null) {
            systemLogs.setUserId(user.getId());
            systemLogs.setUserName(user.getName());
            systemLogs.setResponseTime(new Date());
            systemLogs.setResponseCode(((HttpServletResponse) response).getStatus());
            // systemLogs.setResponseData(JSONObject.toJSONString(data));
            logsDao.updateById(systemLogs);
        }
    }
}
