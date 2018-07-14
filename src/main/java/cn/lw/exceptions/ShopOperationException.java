package cn.lw.exceptions;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.exceptions
 * @date 2018/6/18
 */
public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg) {
        super(msg);
    }
}
