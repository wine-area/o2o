package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class ProductImg {
    private Integer productImgId;

    private String imgAddr;

    private String imgDesc;

    private Integer priority;

    private Date createTime;

    private Integer productId;

}