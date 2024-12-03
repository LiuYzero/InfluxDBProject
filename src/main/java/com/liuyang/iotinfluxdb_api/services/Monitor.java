package com.liuyang.iotinfluxdb_api.services;

import lombok.AllArgsConstructor;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 创建定时任务，模拟上报数据，并写入InfluxDB
 */
@Service
@AllArgsConstructor
public class Monitor {
    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    private InfluxDB influxDB;

//    @Scheduled(fixedRate = 1000)
    public void writeQPS(){
        logger.info("start");
        int data = (int) (Math.random()*100);

        Point point = Point.measurement("ApiQPS")
                .tag("url","/example")
                .addField("count", data)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        influxDB.write("espiot","autogen", point);

        logger.info("upload count data: "+ data);
    }
}
