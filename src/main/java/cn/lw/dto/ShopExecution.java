package cn.lw.dto;

import cn.lw.domain.Shop;
import cn.lw.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/6/18
 */
@Getter@Setter
public class ShopExecution {

    //状态值
    private int state;
    //状态信息
    private String stateInfo;
    //店铺数量
    private int count;
    //店铺 增删改的时候用到
    private Shop shop;
    //店铺列表 查询时用到
    private List<Shop> shopList;

    /**
     * 店铺操作失败的构造器
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功的构造器
     * @param shopStateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
        this.shop=shop;
    }

    public ShopExecution() {
    }

    @Override
    public String toString() {
        return "ShopExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", count=" + count +
                ", shop=" + shop +
                ", shopList=" + shopList +
                '}';
    }

    /**
     *店铺操作成功的构造器
     * @param shopStateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shopList){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
        this.shopList=shopList;
    }
}
