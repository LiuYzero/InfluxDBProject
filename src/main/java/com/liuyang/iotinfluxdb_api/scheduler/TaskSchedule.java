package com.liuyang.iotinfluxdb_api.scheduler;

import com.liuyang.iotinfluxdb_api.monitor.IotMonitorServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedule {
    public  static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedule.class);
    @Autowired
    IotMonitorServices iotMonitorServices;

    @Scheduled(cron="*/10 * *  * * ?")
    public void monitorReport(){
        iotMonitorServices.monitorReport();
        LOGGER.info("executed iotMonitorServices.monitorReport();");
    }

}
