package com.itheima.dao.impl;

import com.itheima.dao.UserMapper;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    SqlSessionFactory sqlSessionFactory;

    public UserMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User findById(Integer id) {
        //1.搞出一个sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.通过sqlSession去查询
        User user = sqlSession.selectOne("UserMapper.findById",id);
        //3.关闭sqlSession
        sqlSession.close();

        //4.返回结果
        return user;
    }

    public List<User> findByUserName(String username) {
        //1.搞出一个sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.通过sqlSession去查询

        List<User> users = sqlSession.selectList("UserMapper.findByUserName", username);
        //3.关闭sqlSession
        sqlSession.close();
        //4.返回结果
        return users;
    }

    public int addUser(User user) {
        //1.搞出一个sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.通过sqlSession去查询
        int insert = sqlSession.insert("UserMapper.addUser", user);
        //3.提交
        sqlSession.commit();
        //4.关闭sqlSession
        sqlSession.close();
        //5.返回结果
        return insert;
    }
}
