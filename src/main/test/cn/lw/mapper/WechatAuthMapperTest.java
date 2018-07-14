package cn.lw.mapper;

import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/13
 */
public class WechatAuthMapperTest extends BaseTest {
    @Autowired
    WechatAuthMapper wechatAuthMapper;
    @Test
    public void queryWechatAuthByOpenId() {
        System.out.println( wechatAuthMapper.queryWechatAuthByOpenId( "ovLbns-gxJHqC-UTPQKvgEuENl-E" ) );
    }
}