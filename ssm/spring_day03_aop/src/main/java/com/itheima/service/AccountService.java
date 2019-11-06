package com.itheima.service;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AccountService {
    public void save();

    public String delete();

    public void queryAll();

    public void queryById();

    public void addAccount();
}
