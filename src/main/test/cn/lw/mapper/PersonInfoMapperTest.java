package cn.lw.mapper;

import cn.lw.domain.PersonInfo;
import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/13
 */
public class PersonInfoMapperTest extends BaseTest {

    @Autowired
    PersonInfoMapper personInfoMapper;
    @Test
    public void insert() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setCreateTime( new Date() );
        personInfo.setEmail( "123456@qq.com" );
        personInfo.setEnableStatus( 1 );
        personInfo.setGender( "男" );
        personInfo.setName( "梨花" );
        personInfo.setLastEditTime( new Date() );
        personInfo.setUserType( 1 );
        personInfoMapper.insert( personInfo );
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println( personInfoMapper.selectByPrimaryKey( 9 ) );

    }
}