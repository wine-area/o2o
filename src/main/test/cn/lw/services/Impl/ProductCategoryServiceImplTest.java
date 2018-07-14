package cn.lw.services.Impl;

import cn.lw.services.BaseTest;
import cn.lw.services.IProductCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/24
 */
public class ProductCategoryServiceImplTest extends BaseTest {
    @Autowired
    IProductCategoryService productCategoryService;
    @Test
    public void selectAllByShopId() {
        productCategoryService.selectAllByShopId
                ( 28 ).forEach( (i)-> System.out.println(i) );
    }
}