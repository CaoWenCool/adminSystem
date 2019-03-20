package com.demo.adminsystem.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:15
 * @version: V1.0
 * @detail:
 **/
public class RequestUtil {

    /**
     * 获取当前项目的webroot的方法
     * @param request
     * @return
     */
    public static String getAppURL(HttpServletRequest request){
        if(request == null){
            return "";
        }
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if( port < 0){
            port = 80;
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))){
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
}
