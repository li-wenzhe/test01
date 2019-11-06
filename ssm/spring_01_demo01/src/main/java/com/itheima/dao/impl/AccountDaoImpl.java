package com.itheima.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.itheima.bean.Account;
import com.itheima.dao.AccountDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    /*private DruidDataSource dataSource;

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }*/
    private QueryRunner queryRunner;

    /*public AccountDaoImpl(QueryRunner queryRunner){
        this.queryRunner = queryRunner;
    }*/

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public void save(Account account) throws SQLException {
        //1. 创建连接池对象
        /*DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");*/




        //2. 创建QueryRunner对象
        /*QueryRunner queryRunner = new QueryRunner(dataSource);*/
        //3. 执行sql语句
        queryRunner.update("insert into values(?,?,?)",null,account.getName(),account.getMoney());

    }

    @Override
    public void update(Account account) throws SQLException {
        //1. 创建连接池对象
        /*DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);*/
        //3. 执行sql语句
        queryRunner.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());

    }

    @Override
    public void delete(Integer id) throws SQLException {
        //1. 创建连接池对象
       /* DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);*/
        //3. 执行sql语句
        queryRunner.update("delete from  account  where id=?",id);

    }

    @Override
    public Account findById(Integer id) throws SQLException {
        //1. 创建连接池对象
       /* DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);*/
        //3. 执行sql语句
        return queryRunner.query("select * from  account  where id=?",new BeanHandler<Account>(Account.class),id);
    }

    @Override
    public List<Account> findAll() throws SQLException {

        //1. 创建连接池对象
        /*DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);*/
        //3. 执行sql语句
        return queryRunner.query("select * from  account ",new BeanListHandler<Account>(Account.class));
    }
}
