package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class Shop {
    private Integer shopId;

    private String shopName;

    private String shopDesc;//店铺描述

    private String shopAddr;//店铺具体地址

    private String phone;

    private String shopImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;
    //-1.不可用 0.审核中 1.可用
    private Integer enableStatus;
    //超级管理员给店家的提醒
    private String advice;
    //区域实体类：表示店铺属于哪一块区域
    private Area area;
    //店铺类别实体类
    private ShopCategory shopCategory;
    //用户信息实体类：表示店铺由谁创建
    private PersonInfo owner;

}