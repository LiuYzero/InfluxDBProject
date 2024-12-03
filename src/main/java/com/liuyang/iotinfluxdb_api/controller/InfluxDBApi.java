package com.liuyang.iotinfluxdb_api.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuyang.iotinfluxdb_api.entity.ResponseResult;
import com.liuyang.iotinfluxdb_api.services.DBService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/influxdb")
public class InfluxDBApi {

    private static final Logger logger =  LoggerFactory.getLogger(InfluxDBApi.class);

    @Resource
    DBService dbService;

    @PostMapping("/insertData")
    public ResponseResult influxdbInsertData(@RequestBody JSONObject data){
        logger.info("insertData {}", data.toJSONString());
        dbService.insertData(data);
        return ResponseResult.success();
    }
}
