package com.demo.adminsystem.core.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:25
 * @version: V1.0
 * @detail:
 **/
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        try {
            body = IOUtils.toByteArray(request.getInputStream());
            String bodyStr = new String(body, Charset.forName("UTF-8"));
            if (StringUtils.isNotBlank(bodyStr)) {
                JSONObject json = JSONObject.parseObject(bodyStr);
                for (String key : json.keySet()) {
                    Object param = json.get(key);
                    if (param != null) {
                        if (param instanceof String) {
                            String paramStr = (String) param;
                            if (StringUtils.isBlank(paramStr)) {
                                json.put(key, null);
                            }
                        }
                    }
                }
                body = json.toString().getBytes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
