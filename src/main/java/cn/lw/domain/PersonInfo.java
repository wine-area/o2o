package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter@Setter@ToString
public class PersonInfo {
    private Integer userId;

    private String name;

    private String profileImg;

    private String email;

    private String gender;

    //'0:禁止使用本商城,1：  许使用本商场'
    private Integer enableStatus;

    //1.顾客 2.店家 3.超级管理员
    private Integer userType;

    private Date createTime;

    private Date lastEditTime;

}