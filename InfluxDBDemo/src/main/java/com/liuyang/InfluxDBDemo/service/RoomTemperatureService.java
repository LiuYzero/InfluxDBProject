package com.liuyang.InfluxDBDemo.service;

import com.liuyang.InfluxDBDemo.entity.RoomTemperature;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 房间温度服务类
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class RoomTemperatureService {

    @Autowired
    private InfluxDB influxDb;

    public Boolean saveRoomTemperature(RoomTemperature roomTemperature){
        try {
            Point point = Point.measurement("room_temperature")
                    .tag("category", "iot")
                    .addField("rome_id", roomTemperature.getRoomId())
                    .addField("temperature", roomTemperature.getTemperature())
                    .addField("timestamp", roomTemperature.getTimestamp() == null ? 0L : roomTemperature.getTimestamp())
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .build();

            // if timestamp is not nullabled , use addFieldBFromPOJO

            influxDb.write("iotdata", "autogen", point);

            return Boolean.TRUE;
        }catch (Exception e){
            log.error(e.getMessage()+";"+e.getCause()+";"+e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
}
