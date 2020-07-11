package com.bdqn.myappinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(value = "com.bdqn.myappinfo.dao")
@SpringBootApplication
public class MyappinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappinfoApplication.class, args);
    }

}
