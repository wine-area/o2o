package cn.lw.services.Impl;
import cn.lw.domain.Area;
import cn.lw.services.IAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class AreaServiceImplTest {
    @Autowired
    IAreaService service;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
/*
        Area area=new Area();
        area.setAreaName( "成都工业学院食堂" );
        area.setAreaPriority( 2 );
        area.setCreateTime( new Date() );
        try {
            Thread.sleep( 2000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        area.setLastEditTime( new Date(  ) );
        service.insert( area );
*/

    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        service.selectAll().forEach( (i)-> System.out.println(i) );
    }

    @Test
    public void updateByPrimaryKey() {
    }
}