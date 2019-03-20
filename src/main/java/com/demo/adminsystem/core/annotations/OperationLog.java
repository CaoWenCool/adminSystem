package com.demo.adminsystem.core.annotations;

import java.lang.annotation.*;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:08
 * @version: V1.0
 * @detail:
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    /**
     * 模块
     *
     * @return
     */
    String module();

    /**
     * 操作描述
     *
     * @return
     */
    String operation();

    /**
     * 备注
     *
     * @return
     */
    String remark() default "";

    boolean isAES() default false;

}
