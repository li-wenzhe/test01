package com.itheima.jopo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Account implements Serializable{
    private Integer id;
    private String name;
    private double money;

    private Address address;
}
