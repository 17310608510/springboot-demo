package com.example.demo.controller.testWeb;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.test.Users;
import com.example.demo.service.test.UsersService;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午4:12:13 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
@RestController
public class UsersController {
	@Autowired
    private UsersService usersService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid Users user, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return usersService.addUser(user);
    }
}
