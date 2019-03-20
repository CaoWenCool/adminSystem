package com.demo.adminsystem.core.exception;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 11:27
 * @version: V1.0
 * @detail: 自定义异常  注意：此异常为check Exception
 **/
public class PlatformException extends Exception{

    private static final long serialVersionUID = 1L;

    protected Throwable cause = null;

    public PlatformException(){

    }

    public PlatformException(String msg){
        super(msg);
    }

    public PlatformException(String message,Throwable cause){
        super(message);
        this.cause = cause;
    }

    public PlatformException(Throwable cause){
        super(cause.getMessage());
        this.cause = cause;
    }

    @Override
    public synchronized Throwable getCause() {
        return this.cause;
    }
}
