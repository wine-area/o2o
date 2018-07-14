package cn.lw.exceptions;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.exceptions
 * @date 2018/7/4
 */
public class ProductOperationException extends RuntimeException {
    public ProductOperationException(String message) {
        super( message );
    }
}
