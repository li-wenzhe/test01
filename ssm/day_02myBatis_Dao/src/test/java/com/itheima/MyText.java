package com.itheima;

import com.itheima.dao.UserMapper;
import com.itheima.dao.impl.UserMapperImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


public class MyText {
    static UserMapper mapper;
    static {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取代理类
        mapper =new UserMapperImpl(sqlSessionFactory);//sqlSession.getMapper(UserMapper.class);
        }
    @Test
    public void test01(){
        System.out.println(mapper.findById(0));
    }
    @Test
    public void test02(){
        System.out.println(mapper.findByUserName("二"));
    }
    @Test
    public void test03(){
        User user = new User();
        user.setBirthday(new Date());
        user.setAddress("福建");
        user.setSex("女");
        user.setUsername("狗蛋");
        mapper.addUser(user);
        System.out.println(user);
    }
}


