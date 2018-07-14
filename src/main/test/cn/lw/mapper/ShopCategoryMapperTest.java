package cn.lw.mapper;

import cn.lw.domain.ShopCategory;
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
public class ShopCategoryMapperTest extends BaseTest {

    @Autowired
    ShopCategoryMapper ShopCategoryMapper;
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
        ShopCategory test = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId( 10 );
        test.setParent( parent );

        ShopCategoryMapper.selectAll( null ).forEach( (i) -> System.out.println( i ) );
    }

    @Test
    public void updateByPrimaryKey() {
    }
}