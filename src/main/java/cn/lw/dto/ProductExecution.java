package cn.lw.dto;

import cn.lw.domain.Product;
import cn.lw.enums.ProductStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/4
 */
@Getter@Setter
public class ProductExecution {

    private String stateInfo;

    private int state;

    private int count;

    private Product product;

    private List<Product> products;

    public ProductExecution() {
    }

    public ProductExecution(ProductStateEnum productStateEnum, Product product) {
        this.product = product;
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }

    public ProductExecution(ProductStateEnum productStateEnum, List<Product> products) {
        this.products = products;
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }

    public ProductExecution(ProductStateEnum productStateEnum) {
        this.stateInfo = productStateEnum.getStateInfo();
        this.state = productStateEnum.getState();
    }
}
