package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter@Setter@ToString
public class Product {
    private Integer productId;

    private String productName;

    private String productDesc;
    //简略图
    private String imgAddr;

    private String normalPrice;

    private String promotionPrice;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;
    //-1.不可用 ,0.下架 1.在前端展示系统展示
    private Integer enableStatus;

    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;

}