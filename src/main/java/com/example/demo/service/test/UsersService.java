package com.example.demo.service.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.test.Users;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午4:16:11
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class UsersService {
	@Resource
	public UsersDao usersDao;

	public String addUser(Users users) {
		return "";
	}
}
