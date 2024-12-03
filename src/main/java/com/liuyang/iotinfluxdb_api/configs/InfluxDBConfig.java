package com.liuyang.iotinfluxdb_api.configs;


import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class InfluxDBConfig {

    @Value("${spring.influx.user}")
    private String userName;

    @Value("${spring.influx.password}")
    private String password;

    @Value("${spring.influx.url}")
    private String url;

    //数据库
    @Value("${spring.influx.database}")
    private String database;


    @Bean
    public InfluxDB createInfluxdb(){
        InfluxDB influxDB = InfluxDBFactory.connect(url, userName,password);
        influxDB.setDatabase(database);
        return influxDB;
    }
}
