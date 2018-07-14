package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class ShopCategory {
    private Integer shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private ShopCategory parent;

}