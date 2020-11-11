package com.example.demo.service.test;

import com.example.demo.model.SendEmailModel;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月29日 上午11:37:30 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
public interface EmailService {
	void sendEmail (String emailKey, SendEmailModel model) ;
}
