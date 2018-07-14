package cn.lw.mapper;

import cn.lw.domain.Product;
import cn.lw.domain.ProductCategory;
import cn.lw.domain.Shop;
import cn.lw.services.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/4
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductMapperTest extends BaseTest {

    @Autowired
    ProductMapper productMapper;
    @Test
    public void testAInsert() {
        Product product = new Product();
        product.setCreateTime( new Date() );
        product.setEnableStatus( 1 );
        product.setPriority( 21 );
        product.setProductName( "商品1" );
        Shop shop = new Shop();
        shop.setShopId( 30 );
        product.setShop( shop );
        productMapper.insert( product );
    }


    @Test
    public void testCDelete() {
        productMapper.deleteByPrimaryKey( 13 );

    }

    @Test
    public void testSelectById() {
        System.out.println( productMapper.selectByPrimaryKey( 21 ) );

    }

    @Test
    public void testUpdate() {
       /* Product product = productMapper.selectByPrimaryKey( 18 );
        product.setNormalPrice( "22" );
        product.setPriority( 4 );
        productMapper.updateByPrimaryKey( product );*/
    }

    @Test
    public void testQueryProductList() {
        Product product = new Product();
    /*    product.setProductName( "大" );
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId( 4 );
        product.setProductCategory( productCategory );*/
        product.setEnableStatus( 1 );
        Shop shop = new Shop();
        shop.setShopId( 28 );
        product.setShop( shop );
        System.out.println( productMapper.selectCount( product ) );
        productMapper.queryProductList( product, 0, 8 ).forEach( i -> System.out.println( i ) );
    }

    @Test
    public void updateProductCategory2Null() {
        int i = productMapper.updateProductCategory2Null( 25 );
    }
}