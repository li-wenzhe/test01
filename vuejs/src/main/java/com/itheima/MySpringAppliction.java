package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*运行起步类（main方法），会自动启动内置的tomcat服务器，方便开发。*/
@SpringBootApplication
public class MySpringAppliction {
    public static void main(String[] args) {
            SpringApplication.run(MySpringAppliction.class,args);
    }
}
