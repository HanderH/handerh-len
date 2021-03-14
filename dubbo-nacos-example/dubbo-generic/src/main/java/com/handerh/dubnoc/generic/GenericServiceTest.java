package com.handerh.dubnoc.generic;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author handerh
 * @date 2021/03/14
 */
@Service
public class GenericServiceTest {

    public  void gentest() {

        // 引用远程服务
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        // 弱类型接口名
        reference.setInterface("com.handerh.dubnac.api.facade.service.ProviderService");
//        reference.setVersion("1.0.0");
        // 声明为泛化接口
        reference.setGeneric("true");

        GenericService genericService = reference.get();
        HashMap<String, Object> repMap = new HashMap<String, Object>();

        Map<String,Object> getUserInfo = (Map<String, Object>) genericService.$invoke("getUserInfo", new String[]{Map.class.getName()}, new Object[]{repMap});

        System.out.println(getUserInfo);
    }
}
