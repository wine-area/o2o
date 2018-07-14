package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class Area {
    // ID
    private Integer areaId;
    // 名称
    private String areaName;
    // 权重
    private Integer areaPriority;
    // 创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;


}