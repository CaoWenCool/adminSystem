package com.demo.adminsystem.core.plugins;

import com.demo.adminsystem.AdminsystemApplication;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 16:54
 * @version: V1.0
 * @detail:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdminsystemApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringEmailTest {

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    Environment environment;

    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1042879511@qq.com");
        message.setTo("3441955411@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo("1162942521@qq.com");
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashedMap();
        model.put("username", "didi");
        VelocityContext context = new VelocityContext();
        for(String key : model.keySet()) {
            context.put(key, model.get(key));
        }

        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/email.vm", "UTF-8", context, stringWriter);
        String text = stringWriter.toString();
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }
}
