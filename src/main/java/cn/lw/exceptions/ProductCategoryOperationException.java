package cn.lw.exceptions;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.exceptions
 * @date 2018/7/3
 */
public class ProductCategoryOperationException extends RuntimeException{


    public ProductCategoryOperationException(String message) {
        super( message );
    }
}
