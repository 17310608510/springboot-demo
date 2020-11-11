package com.example.demo.service.impl.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.SendEmailModel;
import com.example.demo.service.test.EmailService;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月29日 上午11:39:00 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
@Component
@Service
public class EmailServiceImpl implements EmailService{

	@Async("taskExecutor")
	@Override
    public void sendEmail(String emailKey, SendEmailModel model) {
//        try{
//            // 异步执行
//            Thread.sleep(1000);
//            String textBody = EmailUtil.convertTextModel(BodyType.getByCode(emailKey),"知了","一笑");
//            // 发送文本邮件
//            EmailUtil.sendEmail01(model.getReceiver(), EmailType.getByCode(emailKey),textBody);
//            // 发送复杂邮件:文本+图片+附件
//            String body = "自定义图片：<img src='cid:gzh.jpg'/>,网络图片：<img src='http://pic37.nipic.com/20140113/8800276_184927469000_2.png'/>";
//            // EmailUtil.sendEmail02(model.getReceiver(),"文本+图片+附件",body);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }
	
}
