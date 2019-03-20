package com.demo.adminsystem.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:32
 * @version: V1.0
 * @detail: 读取properties文件资源工具类
 **/
public class PropertiesUtil implements AutoCloseable{
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 读取app.properties资源文件的工具类
     * @param key
     * @param propFileUrl
     * @return
     */
    public static String getProps(String key,String propFileUrl){
        Properties props = new Properties();
        try (InputStreamReader in = new InputStreamReader(PropertiesUtil.class.getResourceAsStream(propFileUrl), "UTF-8")) {
            props.load(in);
            return props.getProperty(key);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 读取app.properties资源文件的工具类
     * @param key
     * @param defult  当查询不到key的情况下，返回defult
     * @param propFileUrl
     * @return
     */
    public static String getProps(String key,String defult,String propFileUrl){
        Properties props = new Properties();
        try (InputStreamReader in = new InputStreamReader(PropertiesUtil.class.getResourceAsStream(propFileUrl), "UTF-8")) {
            props.load(in);
            String property = props.getProperty(key);
            if (StringUtils.isBlank(property)) {
                return defult;
            }
            return property;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return defult;
    }

    @Override
    public void close() throws IOException {
    }

}
