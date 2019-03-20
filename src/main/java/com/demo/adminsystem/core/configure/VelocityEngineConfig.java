package com.demo.adminsystem.core.configure;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 16:44
 * @version: V1.0
 * @detail: VelocityEngineConfig 模板渲染引擎配置
 **/
@Configuration
public class VelocityEngineConfig {

    @Autowired
    Environment environment;

    @Bean(name = "velocityEngine")
    public VelocityEngine velocityEngine() throws Exception {

        Properties properties = new Properties();
        String[] paramsNames = new String[]{"input.encoding", "output.encoding", "resource.loader", "class.resource.loader.class"};
        for (String name : paramsNames) {
            properties.setProperty(name, environment.getProperty("velocity.engine." + name));
        }
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        return velocityEngine;
    }
}
