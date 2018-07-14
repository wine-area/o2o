package cn.lw.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter@Setter@ToString
public class WechatAuth {

    private Integer wechatAuthId;

    private String openId;

    private PersonInfo user;

    private Date createTime;

}