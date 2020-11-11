package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午2:51:47 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
@MappedSuperclass
public class ParentEntity {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_Time")
	private Date updateTime; //修改时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_Time")
	private Date createTime; //创建时间
	
	@Column(name = "flag")
	private Integer flag; //0正常  1删除 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "delete_time")
	private Date deleteTime;//删除时间
 	
	public ParentEntity() {
		this.updateTime = new Date(System.currentTimeMillis());
		this.createTime = new Date(System.currentTimeMillis());
		this.flag = 0;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Date getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
}
