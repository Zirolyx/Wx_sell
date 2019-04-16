package com.xmcc.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData<T> {
    private int code;
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)//返回json时忽略为null的属性
    private T data;

    public JsonData(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 请求失败后返回数据
     */

    //失败不携带数据、信息
    public static <T>JsonData fail(){
        return new JsonData<>(ResultEnums.FAIL.getCode(),ResultEnums.FAIL.getMsg());
    }

    //失败携带信息不携带数据
    public static <T>JsonData fail(String msg){
        return new JsonData<>(ResultEnums.FAIL.getCode(),msg);
    }

    //失败携带信息、数据
    public static <T>JsonData fail(String msg,T t){
        return new JsonData<>(ResultEnums.FAIL.getCode(),msg,t);
    }

    //失败携带数据不携带信息
    public static <T>JsonData fail(T t){
        return new JsonData<>(ResultEnums.FAIL.getCode(),ResultEnums.FAIL.getMsg(),t);
    }

    /**
     * 请求成功后返回数据
     */

    //成功后不携带数据
    public static <T>JsonData success(){
        return new JsonData<>(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg());
    }

    //成功后携带数据
    public static <T>JsonData success(T t){
        return new JsonData<>(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),t);
    }

}
