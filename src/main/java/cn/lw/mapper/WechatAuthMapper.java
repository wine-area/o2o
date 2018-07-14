package cn.lw.mapper;

import cn.lw.domain.WechatAuth;
import java.util.List;

public interface WechatAuthMapper {
    int deleteByPrimaryKey(Integer wechatAuthId);

    int insert(WechatAuth record);

    WechatAuth selectByPrimaryKey(Integer wechatAuthId);

    List<WechatAuth> selectAll();

    int updateByPrimaryKey(WechatAuth record);

    WechatAuth queryWechatAuthByOpenId(String openId);
}