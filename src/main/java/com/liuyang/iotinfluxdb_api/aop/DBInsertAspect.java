package com.liuyang.iotinfluxdb_api.aop;


import com.alibaba.fastjson.JSONObject;
import com.liuyang.iotinfluxdb_api.services.DBService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Aspect
@Component
public class DBInsertAspect {
    private static final Logger logger = LoggerFactory.getLogger(DBInsertAspect.class);

    private Long startTime = System.currentTimeMillis();

    @Autowired
    DBService dbService;

    @Autowired
    private Environment environment;


    @Pointcut("execution(public * com.liuyang.iotinfluxdb_api.controller.InfluxDBApi.*(..))")
    public void webLog(){}

//    @Before("webLog()")
    public void doBefore(){

    }

    @Around("webLog()")
    public Object recordCostTime(ProceedingJoinPoint joinPoint) {
        Long startTime2 = System.currentTimeMillis();

        Object result = "";
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("error: ", e);
        }finally {
            logger.info("recordCostTime : {} ms", System.currentTimeMillis() - startTime2);
            Long costTime = System.currentTimeMillis() - startTime2;

            JSONObject interfaceData = new JSONObject();
            interfaceData.put("database", "db_iot");
            interfaceData.put("table","t_interface");
            JSONObject tags = new JSONObject();
            tags.put("interface", environment.getProperty("spring.application.name")+"/controller/InfluxDBApi."+joinPoint.getSignature().getName());

            JSONObject fields = new JSONObject();
            fields.put("costTime", costTime);

            interfaceData.put("tags", tags);
            interfaceData.put("fields", fields);

            logger.info("aop minioData: {}", interfaceData);
            dbService.insertData(interfaceData);
        }

        return result;
    }

//    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturing(JoinPoint joinPoint, Object ret){
        logger.info("Time-Consuming: {} ms", System.currentTimeMillis() - startTime);
        Long costTime = System.currentTimeMillis() - startTime;

        JSONObject interfaceData = new JSONObject();
        interfaceData.put("database", "db_iot");
        interfaceData.put("table","t_interface");
        JSONObject tags = new JSONObject();
        tags.put("interface", environment.getProperty("spring.application.name")+"/controller/InfluxDBApi."+joinPoint.getSignature().getName());

        JSONObject fields = new JSONObject();
        fields.put("costTime", costTime);

        interfaceData.put("tags", tags);
        interfaceData.put("fields", fields);

        logger.info("aop minioData: {}", interfaceData);
        dbService.insertData(interfaceData);
    }



}
