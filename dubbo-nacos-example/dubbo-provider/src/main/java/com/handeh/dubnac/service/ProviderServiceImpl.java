package com.handeh.dubnac.service;

import com.handerh.dubnac.api.facade.response.UserInfoResp;
import com.handerh.dubnac.api.facade.service.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author handerh
 * @date 2021/03/13
 */
@DubboService(interfaceClass = ProviderService.class, timeout = 120000)
public class ProviderServiceImpl implements ProviderService {


    public String providerTest1() {
        return "Hello Consumer,I am Provider";
    }

    public Map<String,Object> getUserInfo(Map<String,Object> map) {

        HashMap<String, Object> respMap = new HashMap<String, Object>();

        UserInfoResp userInfoResp = UserInfoResp.builder().userName("HanderH").userAge("18").useSex("Man").build();
        respMap.put("body",userInfoResp);
        return respMap;
    }
}
