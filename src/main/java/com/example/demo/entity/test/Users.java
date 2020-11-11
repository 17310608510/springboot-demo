package com.example.demo.entity.test;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午4:10:40
 * @version 1.0
 * @parameter
 * @since Validator + BindResult 进行校验
 * 
 *        Validator
 *        可以非常方便的制定校验规则，并自动帮你完成校验。首先在入参里需要校验的字段加上注解,
 *        每个注解对应不同的校验规则，并可制定校验失败后的信息：
 * 
 * @return
 */
@Data
public class Users {
	@NotNull(message = "用户id不能为空")
	private Long id;

	@NotNull(message = "用户账号不能为空")
	@Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
	private String account;

	@NotNull(message = "用户密码不能为空")
	@Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
	private String password;

	@NotNull(message = "用户邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	private String email;
}
