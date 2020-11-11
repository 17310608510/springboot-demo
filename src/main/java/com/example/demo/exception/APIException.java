package com.example.demo.exception;

import lombok.Getter;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午4:01:30
 * @version 1.0
 * @parameter
 * @since 自定义异常
 * @return
 */
@Getter // 只要getter方法，无需setter
public class APIException extends RuntimeException {
	private int code;
	private String msg;

	public APIException() {
		this(1001, "接口错误");
	}

	public APIException(String msg) {
		this(1001, msg);
	}

	public APIException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
}
