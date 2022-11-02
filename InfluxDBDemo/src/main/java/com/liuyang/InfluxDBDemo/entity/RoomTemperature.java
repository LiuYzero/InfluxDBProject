package com.liuyang.InfluxDBDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.influxdb.annotation.Column;

import java.time.DateTimeException;

/**
 * 房间温度数据类 for InfluxDB
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomTemperature {

    private String roomId;
    private Double temperature;
    private Long timestamp;

}
