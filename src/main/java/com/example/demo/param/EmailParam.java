package com.example.demo.param;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月29日 上午11:08:28
 * @version 1.0
 * @parameter
 * @since 邮箱发送参数配置
 * @return
 */
public class EmailParam {
	/**
	 * 邮箱服务器地址
	 */
	// public static final String emailHost = "smtp.mxhichina.com" ;
	// 阿里云企业邮箱配置（账号+密码）
	// public static final String emailHost = "smtp.aliyun.com" ; 阿里云个人邮箱配置（账号+密码）
	public static final String emailHost = "smtp.163.com"; // 网易邮箱配置（账号+授权码）
	/**
	 * 邮箱协议
	 */
	public static final String emailProtocol = "smtp";
	/**
	 * 邮箱发件人
	 */
	public static final String emailSender = "xxxxxx@163.com";
	/**
	 * 邮箱授权码
	 */
	public static final String password = "authCode";
	/**
	 * 邮箱授权
	 */
	public static final String emailAuth = "true";
	/**
	 * 邮箱昵称
	 */
	public static final String emailNick = "知了一笑";

}
