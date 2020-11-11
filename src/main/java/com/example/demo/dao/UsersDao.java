package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.test.Users;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午4:21:10 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
@Repository
public interface UsersDao extends JpaRepository<Users,Integer>{
	// 但条件查询
    Users findByAge(Integer age);
   
    // 自定义查询
    @Query("from Users u where u.name=:name")
    Users sfindSql(@Param("name") String name);
}
