package com.handerh.dubnac.service;

import com.handerh.dubnac.api.facade.service.ConsumerService;
import com.handerh.dubnac.api.facade.service.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author handerh
 * @date 2021/03/13
 */
@DubboService
public class ConsumerServiceImpl implements ConsumerService {

    @DubboReference(interfaceClass = ProviderService.class,check = false)
    private ProviderService providerService;

    public void printRes() {
        String s = providerService.providerTest1();
        System.out.println(s);
    }
}
