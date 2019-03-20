package com.demo.adminsystem.core.annotations;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:07
 * @version: V1.0
 * @detail:
 **/
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {
}
