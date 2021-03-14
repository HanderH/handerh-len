package com.handerh.dubnoc.controller;

import com.handerh.dubnoc.generic.GenericServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author handerh
 * @date 2021/03/14
 */
@RestController
public class TestController {

    @Autowired
    GenericServiceTest genericServiceTest;

    @GetMapping("test")
    public void test(){

        genericServiceTest.gentest();
    }

}
