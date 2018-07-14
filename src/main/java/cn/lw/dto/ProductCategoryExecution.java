package cn.lw.dto;

import cn.lw.domain.ProductCategory;
import cn.lw.enums.ProductCategoryStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/3
 */
@Getter@Setter
public class ProductCategoryExecution {


    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    /**
     * 操作失败的时候使用的构造器
     * @param stateEnum
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 操作成功的时候使用的构造器
     * @param stateEnum
     * @param productCategoryList
     */
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,
                                    List<ProductCategory> productCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public ProductCategoryExecution() {
    }
}
