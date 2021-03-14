package com.handerh.dubnoc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author handerh
 * @date 2021/03/14
 */
@SpringBootApplication
@EnableDubbo
public class GenericApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenericApplication.class);
    }
}
