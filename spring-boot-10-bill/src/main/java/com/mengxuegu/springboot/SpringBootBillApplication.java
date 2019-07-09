package com.mengxuegu.springboot;

import com.mengxuegu.springboot.mapper.ProviderMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mengxuegu.springboot.mapper")
@SpringBootApplication
public class SpringBootBillApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootBillApplication.class, args);
    }

}
