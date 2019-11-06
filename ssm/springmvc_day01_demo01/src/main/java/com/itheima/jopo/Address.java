package com.itheima.jopo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable{
    private String provinceName;
    private String cityName;
}
