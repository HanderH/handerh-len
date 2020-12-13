package com.handerh.distrublock.reids;

import java.util.HashMap;
import java.util.Map;

/**
 * @author handerh
 * @date 2020/12/10
 */
public class Test {

    public static void main(String[] args) {
        Object obj = "";

        System.out.println(obj instanceof String);

        Map<String,String> map = new HashMap<>();

        map.put("222","bbb");

        System.out.println(map.toString());

        Object obj2 = new HashMap<>();

        System.out.println(obj2 instanceof Map);
    }
}
