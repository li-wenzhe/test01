package com.itheima;


import com.itheima.dao.UserMapper;
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
    static SqlSession sqlSession;
    static UserMapper mapper;
    static {
        //1. 读取SqlMapConfig.xml获得输入流
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
        //3. 获得SqlSession
        sqlSession = sqlSessionFactory.openSession();

        //获取代理类
        mapper = sqlSession.getMapper(UserMapper.class);
        //4.通过namespace.id调用
        //List<User> users = sqlSession.selectList("UserMapper.findAll");//原始开发方法的调用(权限定名.id)
    }
    @Test
    public void testFindAll() throws Exception {//原始开发方法

        List<User> users = mapper.findAll();
        //5.打印输出
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
    }

    @Test
    public void tsxtAdd(){
        User user =new User();
        user.setUsername("二狗");
        user.setSex("男");
        user.setAddress("北京");
        user.setBirthday(new Date());
        int i = mapper.addUser(user);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void textDelete(){
        mapper.deleteUserById(8);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void textUserByName(){
        List<User> users = mapper.findUserByUserName("二");
        /*for (User user : users) {
            System.out.println(user);
        }*/
        System.out.println(users);

        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void textUserByAddress(){
        List<User> users = mapper.findUserByAddress("北");
        System.out.println(users);
        sqlSession.commit();
        sqlSession.close();
    }

}
