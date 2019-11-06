package com.itheima.web;

import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/find")
    public String findAll(Model model) {
        System.out.println("AccountController findAll()");
        List<Account> list = accountService.findAll();
        System.out.println(list);
        model.addAttribute("list", list);

        return "success";
    }
}
