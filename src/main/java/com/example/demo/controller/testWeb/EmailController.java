package com.example.demo.controller.testWeb;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SendEmailModel;
import com.example.demo.param.EmailType;
import com.example.demo.service.test.EmailService;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月29日 下午12:01:39 
    * @version 1.0 
    * @parameter 
    * @since 集成 JavaMail ,实现异步发送邮件
    * @return */
@RestController
public class EmailController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class) ;

	    @Resource
	    private EmailService emailService ;

	    @RequestMapping("/sendEmail")
	    public String sendEmail (){
	        SendEmailModel model = new SendEmailModel() ;
	        model.setReceiver("xxxxx@qq.com");
	        emailService.sendEmail(EmailType.EMAIL_TEXT_KEY.getCode(),model);
	        LOGGER.info("执行结束====>>");
	        return "success" ;
	    }
}
