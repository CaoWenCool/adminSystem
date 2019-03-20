package com.demo.adminsystem.core.plugins.email;

import com.alibaba.fastjson.JSONObject;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 16:20
 * @version: V1.0
 * @detail: 邮件发送模板
 **/
@Service
public class SendTestEmail {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * 发送文本文件
     * @param to
     * @param cc
     * @param subject
     * @param content
     */
    public void sendTextMail(String[] to,String[] cc,String subject,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(javaMailSender.getUsername());
        mailMessage.setTo(to);
        if(cc != null){
            mailMessage.setCc(cc);
        }
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setSentDate(new Date());
        javaMailSender.send(mailMessage);
    }

    public void sendTextMailWithFile(String[] to,String[] cc,String subject,String content,File[] files)throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage,true);
        mailMessage.setFrom(javaMailSender.getUsername());
        mailMessage.setTo(to);
        if(cc != null){
            mailMessage.setCc(cc);
        }
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setSentDate(new Date());
        for(File file:files){
            mailMessage.addAttachment(file.getName(),file);
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendTemplateMail(String[] to, String[] cc, String subject, String templeName, JSONObject model, File[] files) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, true);
        mailMessage.setFrom(javaMailSender.getUsername());
        mailMessage.setTo(to);
        if (cc != null) {
            mailMessage.setCc(cc);
        }
        mailMessage.setSubject(subject);

        VelocityContext context = new VelocityContext();
        for (String key : model.keySet()) {
            context.put(key, model.get(key));
        }
        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate(templeName, "UTF-8", context, writer);
        mailMessage.setText(writer.toString());
        mailMessage.setSentDate(new Date());
        for (File file : files) {
            mailMessage.addAttachment(file.getName(), file);
        }
        javaMailSender.send(mimeMessage);
    }
}
