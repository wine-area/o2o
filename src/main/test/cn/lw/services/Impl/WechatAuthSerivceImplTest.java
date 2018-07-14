package cn.lw.services.Impl;

import cn.lw.domain.PersonInfo;
import cn.lw.domain.WechatAuth;
import cn.lw.services.BaseTest;
import cn.lw.services.IWechatAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/7/13
 */
public class WechatAuthSerivceImplTest extends BaseTest {
    @Autowired
    private IWechatAuthService wechatAuthService;
    @Test
    public void register() {
        WechatAuth wechatAuth=new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName( "service test" );
        personInfo.setUserType( 1 );
        wechatAuth.setUser( personInfo );
        wechatAuth.setOpenId( "9dcxxa2e2" );

        wechatAuthService.register( wechatAuth );
    }
}