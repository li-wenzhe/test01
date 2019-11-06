package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAscept {
    @Before(value = "execution(* com.itheima.service.impl.AccountServiceImpl.save(..))")
    public void checkPrivilege() {// 通知
        System.out.println("权限的校验...");
    }

    @AfterReturning(value = "execution(* com.itheima.service.impl.AccountServiceImpl.delete(..))",returning="obj")
    public void showLog(Object obj) {
        System.out.println("显示删除之后的日志..." );
        System.out.println(obj);
    }

    @Around(value = "execution(* com.itheima.service.impl.AccountServiceImpl.queryAll(..))")
    public void showTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("执行之前的时间:" + System.currentTimeMillis());
        //执行findAll
        proceedingJoinPoint.proceed();
        System.out.println("执行之后的时间:" + System.currentTimeMillis());

    }

}
