package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration//指定当前类是一个Spring配置类,当创建容器时会从该类上加载注解
@ComponentScan("com.itheima")//指定spring在初始化容器时,要扫描的包
public class SpringConfiguration {

    private String url="jdbc:mysql://localhost:3306/spring?characterEncoding=utf8";
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "root";

    @Bean
    public DruidDataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean
    public QueryRunner getQueryRunner(DruidDataSource druidDataSource){
        QueryRunner queryRunner = new QueryRunner(druidDataSource);
        return queryRunner;
    }
}
