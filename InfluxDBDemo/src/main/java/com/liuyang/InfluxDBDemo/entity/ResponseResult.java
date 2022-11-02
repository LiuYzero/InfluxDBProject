package com.liuyang.InfluxDBDemo.entity;

import com.liuyang.InfluxDBDemo.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API接口统一返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static ResponseResult<Void> success(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.SUCCESS.getCode());
        responseResult.setMessage(ResultCode.SUCCESS.getMessage());
        return responseResult;
    }

    public static ResponseResult<Void> faild(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.FAILD.getCode());
        responseResult.setMessage(ResultCode.FAILD.getMessage());
        return responseResult;
    }

}
