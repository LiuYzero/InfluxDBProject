package com.liuyang.InfluxDBDemo.controller;

import com.liuyang.InfluxDBDemo.entity.ResponseResult;
import com.liuyang.InfluxDBDemo.entity.RoomTemperature;
import com.liuyang.InfluxDBDemo.service.RoomTemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/influxdb")
@Slf4j
public class InfluxDBApi {

    @Resource
    RoomTemperatureService roomTemperatureService;

    @PostMapping("/room_temperature")
    public ResponseResult roomTemperature(@RequestBody  RoomTemperature roomTemperature){

        log.info(">>> room temperature is :"+roomTemperature);
        roomTemperatureService.saveRoomTemperature(roomTemperature);
        return ResponseResult.success();
    }
}
