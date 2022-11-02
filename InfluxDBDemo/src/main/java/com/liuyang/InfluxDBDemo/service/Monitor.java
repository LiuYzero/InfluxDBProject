package com.liuyang.InfluxDBDemo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 创建定时任务，模拟上报数据，并写入InfluxDB
 */
@Service
@AllArgsConstructor
@Slf4j
public class Monitor {

    private InfluxDB influxDB;

    @Scheduled(fixedRate = 1000)
    public void writeQPS(){
        int data = (int) (Math.random()*100);

        Point point = Point.measurement("ApiQPS")
                .tag("url","/example")
                .addField("count", data)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        influxDB.write("iotdata","autogen", point);

        log.info("upload count data: "+ data);
    }
}
