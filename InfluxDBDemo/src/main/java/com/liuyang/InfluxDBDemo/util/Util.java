package com.liuyang.InfluxDBDemo.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 常用类
 */
public class Util {

    public static JSONObject getResult(int resultCode){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",resultCode);
        return jsonObject;
    }
}
