package cn.lw.services.Impl;

import cn.lw.domain.PersonInfo;
import cn.lw.domain.WechatAuth;
import cn.lw.dto.WechatAuthExecution;
import cn.lw.enums.WechatAuthStateEnum;
import cn.lw.exceptions.WechatAuthOperationException;
import cn.lw.mapper.PersonInfoMapper;
import cn.lw.mapper.WechatAuthMapper;
import cn.lw.services.IWechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/7/13
 */
@Service
public class WechatAuthSerivceImpl implements IWechatAuthService {

    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private WechatAuthMapper wechatAuthMapper;
    private static Logger log = LoggerFactory.getLogger( WechatAuthSerivceImpl.class );
    @Override
    public WechatAuth queryWechatAuthByOpenId(String openId) {
        return wechatAuthMapper.queryWechatAuthByOpenId( openId );
    }

    @Override
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        if (wechatAuth == null && wechatAuth.getOpenId() == null) {
            throw  new WechatAuthOperationException( WechatAuthStateEnum.NULL_AUTH_INFO.getStateInfo() );
        }
        try {
            wechatAuth.setCreateTime( new Date() );
            PersonInfo user = wechatAuth.getUser();
            if (user != null && user.getUserId() == null) {
                try {
                    user.setCreateTime( new Date() );
                    user.setEnableStatus( 1 );
                    int effectedLine = personInfoMapper.insert( user );
                    if (effectedLine < 1) {
                        throw new WechatAuthOperationException( "添加用户信息失败" );
                    }
                } catch (Exception e) {
                    log.error( "insert personinfo error:" + e.getMessage() );
                    throw new WechatAuthOperationException( "insert personinfo error:" + e.getMessage() );
                }
            }
            int effectedLine = wechatAuthMapper.insert( wechatAuth );
            if (effectedLine < 1) {
                log.error( "添加微信用户信息失败" );
                throw new WechatAuthOperationException( "添加微信用户信息失败" );
            } else {
                return new WechatAuthExecution( WechatAuthStateEnum.SUCCESS );
            }
        } catch (Exception e) {
            log.error( "用户注册失败:"+e.getMessage() );
            throw new WechatAuthOperationException( "用户注册失败:" + e.getMessage() );
        }
    }
}
