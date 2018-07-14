package cn.lw.services;

import cn.lw.domain.WechatAuth;
import cn.lw.dto.WechatAuthExecution;
import cn.lw.exceptions.WechatAuthOperationException;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services
 * @date 2018/7/13
 */
public interface IWechatAuthService {
    WechatAuth queryWechatAuthByOpenId(String openId);

    WechatAuthExecution register(WechatAuth wechatAuth)throws WechatAuthOperationException;
}
