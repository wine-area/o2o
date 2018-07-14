package cn.lw.dto;

import cn.lw.services.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/10
 */
public class WechatUserTest extends BaseTest {

    @Test
    public void testJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WechatUser wechatUser = new WechatUser();
        wechatUser.setProvince( "四川" );
        wechatUser.setCity( "成都" );
        wechatUser.setCountry( "郫县" );
        wechatUser.setSex( 1 );
        objectMapper.writeValue( new File(  "wechatUser.txt"), wechatUser );
    }

    @Test
    public void testReadValue() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
    }

}