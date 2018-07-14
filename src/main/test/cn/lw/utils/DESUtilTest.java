package cn.lw.utils;

import cn.lw.services.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/7/14
 */
public class DESUtilTest extends BaseTest {

    @Test
    public void getEncryptString() {

        System.out.println( DESUtil.getEncryptString( "root" ) );
        System.out.println( DESUtil.getEncryptString( "960115" ) );



    }

    @Test
    public void getDecryptString() {
        System.out.println( DESUtil.getDecryptString( DESUtil.getEncryptString( "flw960115" ) ) );
    }
}