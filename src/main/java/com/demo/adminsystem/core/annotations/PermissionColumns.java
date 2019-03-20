package com.demo.adminsystem.core.annotations;

import java.lang.annotation.*;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:12
 * @version: V1.0
 * @detail:
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionColumns {

    /**
     * 表名称
     *
     * @return
     */
    String table();

}
