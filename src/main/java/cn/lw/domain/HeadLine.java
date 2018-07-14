package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class HeadLine {
    private Integer lineId;

    private String lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

}