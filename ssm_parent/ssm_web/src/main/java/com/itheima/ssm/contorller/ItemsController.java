package com.itheima.ssm.contorller;

import com.itheima.ssm.pojo.Items;
import com.itheima.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    ItemsService itemsService;

    // 查询所有
    @RequestMapping(value = "/list")
    public String findAll(Model model){
        List<Items> items = itemsService.list();
        model.addAttribute("items",items);
        for (Items item : items) {
            System.out.println(item);
        }
        return "items";
    }

    // 保存
    @RequestMapping(value = "/save")
    public String save(Items items){
        itemsService.save(items);
        // 保存完成后，同时定向到items.jsp的时候，可以完成查询
        return "redirect:/items/list";
    }
}
