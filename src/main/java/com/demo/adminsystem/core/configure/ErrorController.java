package com.demo.adminsystem.core.configure;

import com.demo.adminsystem.core.exception.PlatformRuntimeException;
import com.demo.adminsystem.core.util.CommonResult;
import com.demo.adminsystem.core.util.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 11:05
 * @version: V1.0
 * @detail:
 **/
@ControllerAdvice
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController{

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    private static String errorMsg = "服务器错误，请联系管理员";


    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * 处理不可预知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult handleException(Exception e){
        logger.error("异常信息"+ e.getMessage(),e);
        return CommonResult.resultError(errorMsg);
    }

    /**
     * 处理所有的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(PlatformRuntimeException.class)
    @ResponseBody
    public CommonResult handleBusinessException(PlatformRuntimeException e){
        logger.error("异常信息"+ e.getMessage(),e);
        return CommonResult.build(
                e.getCode() != null ? e.getCode(): CommonResult.ResultStatusType.ERROR.status,
                e.getMessage(),
                e.getData());
    }

    public CommonResult getErrorPath(HttpServletRequest request,HttpServletResponse response){
        Exception cause   = this.getCause(request);

        String errMsg = null;
        Integer code = null;
        Object data = null;

        if(cause !=  null && cause instanceof PlatformRuntimeException){
            PlatformRuntimeException exception = (PlatformRuntimeException) cause;
            errMsg = exception.getMessage();
            code = exception.getCode();
            data = exception.getData();
        }

        if(code == null){
            if(response.getStatus() == org.springframework.http.HttpStatus.NOT_FOUND.value()){
                code = response.getStatus();
                errMsg = org.springframework.http.HttpStatus.NOT_FOUND.getReasonPhrase();
                data = request.getRequestURI();
            }
        }
        if(code == null){
            code = CommonResult.ResultStatusType.ERROR.status;
        }
        if(errMsg == null){
            errMsg = errorMsg;
        }
        CommonResult commonResult = CommonResult.build(code,errMsg,data);
        if(cause != null){
            logger.error("异常信息："+cause.getMessage(),cause);
        }else {
            try{
                logger.error("异常信息:" + Constant.OBJECT_MAPPER.writeValueAsString(commonResult));
            }catch (JsonProcessingException e){
                e.printStackTrace();;
            }
        }
        return commonResult;
    }

    private Exception getCause(HttpServletRequest request){
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if(error != null){
            while (error instanceof ServletException && error.getCause() != null){
                error = ((ServletException)error).getCause();
            }
        }
        return (Exception) error;
    }


    @Override
    public String getErrorPath() {
        return this.errorPath;
    }
}
