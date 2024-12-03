package com.liuyang.iotinfluxdb_api.services;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DBService {
    private static final Logger logger = LoggerFactory.getLogger(DBService.class);

    @Autowired
    private InfluxDB influxDB;

    public Boolean insertData(JSONObject data){
        logger.info("insertData {}", data.toJSONString());

        String database = data.getString("database");
        String table = data.getString("table");
        logger.info("{},{}",database, table);

        JSONObject tags = data.getJSONObject("tags");
        JSONObject fields = data.getJSONObject("fields");

        Map<String,String> tagMaps = JSONObject.parseObject(tags.toJSONString(), new TypeReference<Map<String, String>>(){});

        Map<String,Object> fieldMaps = JSONObject.parseObject(fields.toJSONString(), new TypeReference<Map<String, Object>>(){});

        logger.info("{},{}", tagMaps, fieldMaps);

        Point point = Point.measurement(table)
                .tag(tagMaps)
                .fields(fieldMaps)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        influxDB.write(database,"autogen", point);

        logger.info("upload databse {} table {}", database, table);

        return Boolean.TRUE;
    }


}
