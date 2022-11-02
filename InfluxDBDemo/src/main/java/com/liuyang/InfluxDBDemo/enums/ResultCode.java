package com.liuyang.InfluxDBDemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // General Success
    SUCCESS(0,"Success"),

    // General Faild
    FAILD(1,"Faild");

    private final Integer code;
    private final String message;



}
