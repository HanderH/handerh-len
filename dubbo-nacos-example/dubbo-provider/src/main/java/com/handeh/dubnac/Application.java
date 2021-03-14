package com.handeh.dubnac;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author handerh
 * @date 2021/03/13
 */
@SpringBootApplication
@EnableDubbo
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }
}
