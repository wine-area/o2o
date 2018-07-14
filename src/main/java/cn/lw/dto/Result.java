package cn.lw.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/3
 */
@Getter@Setter
public class Result<T> {
    private boolean success;//是否成功

    private T data;//返回的数据

    private String errorMsg;//错误信息

    private int errorCode;//错误代码

    public Result(){

    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, String errorMsg, int errorCode) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
