package com.example.demo.model;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月29日 上午11:26:34
 * @version 1.0
 * @parameter
 * @since 邮件发送参数封装
 * @return
 */
public class SendEmailModel {
	public static final String EMAIL_TEXT_KEY = "email_balance_key";
	public static final String EMAIL_IMAGE_KEY = "email_req_num_key";
	public static final String EMAIL_FILE_KEY = "email_open_account_key";

	/**
	 * 收件人邮箱
	 */
	private String receiver;

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
