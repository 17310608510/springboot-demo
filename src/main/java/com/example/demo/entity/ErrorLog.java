package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午2:45:09 
    * @version 1.0 
    * @parameter 
    * @since 错误日志类
    * @return */
@Data
@Entity
@Table(name = "t_error_log")
public class ErrorLog extends ParentEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	private String url ;//地址
	
	private String params;//参数
	
	private String exceptionDesc;//错误信息
	
	private String header;//请求头
	
	private String projectName;//项目名称
}

