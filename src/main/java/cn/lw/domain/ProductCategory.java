package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class ProductCategory {
    private Integer productCategoryId;

    private String productCategoryName;

    private Integer priority;

    private Date createTime;

    private Integer shopId;
}