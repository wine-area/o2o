package cn.lw.mapper;

import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/6/19
 */
public class AreaMapperTest extends BaseTest {

    @Autowired
    private AreaMapper areaMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        areaMapper.selectAll().forEach( (i) -> System.out.println( i ) );
    }

    @Test
    public void updateByPrimaryKey() {
    }
}