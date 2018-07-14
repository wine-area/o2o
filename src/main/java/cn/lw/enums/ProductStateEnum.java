package cn.lw.enums;

import lombok.Getter;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.enums
 * @date 2018/7/4
 */
@Getter
public enum ProductStateEnum {
    OFFLINE(-1, "非法商品"),
    DOWN(0, "下架"),
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "商品为空");

    private int state;

    private String stateInfo;

    ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ProductStateEnum stateOf(int index) {
        for (ProductStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
