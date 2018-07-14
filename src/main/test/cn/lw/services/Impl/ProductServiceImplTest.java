package cn.lw.services.Impl;

import cn.lw.domain.Product;
import cn.lw.domain.ProductCategory;
import cn.lw.domain.Shop;
import cn.lw.dto.ImageHolder;
import cn.lw.services.BaseTest;
import cn.lw.services.IProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/7/4
 */
public class ProductServiceImplTest extends BaseTest {

    @Autowired
    IProductService productService;
    @Test
    public void insert() throws FileNotFoundException {

        Shop shop = new Shop();
        shop.setShopId( 30 );
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId( 1 );
        Product product = new Product();
        product.setShop( shop );
        product.setPriority( 12 );
        product.setNormalPrice( "65" );
        product.setProductCategory( productCategory );
        product.setProductName( "商品测试" );
        File thumbnail = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\AOP优势.png" );
        File img1 = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\bean作用域.png" );
        File img2 = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\Maven或者Gradle下载.png" );
        ImageHolder Thumbnail = new ImageHolder( new FileInputStream( thumbnail ), "AOP优势.png" );
        ImageHolder productImg1 = new ImageHolder( new FileInputStream( img1 ), "bean作用域.png" );
        ImageHolder productImg2 = new ImageHolder( new FileInputStream( img2 ), "Maven或者Gradle下载.png" );
        List<ImageHolder> productImgs = new LinkedList<>();
        productImgs.add( productImg1 );
        productImgs.add( productImg2 );
        productService.insert( product, Thumbnail, productImgs );
    }

    @Test
    public void testUpdate() throws FileNotFoundException {
/*        Shop shop = new Shop();
        shop.setShopId( 30 );
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId( 1 );
        Product product = new Product();
        product.setProductId( 23 );
        product.setShop( shop );
        product.setPriority( 40 );
        product.setNormalPrice( "69" );
        product.setProductCategory( productCategory );
        product.setProductName( "商品更新测试" );
        File thumbnail = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\AOP优势.png" );
        File img1 = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\bean作用域.png" );
        File img2 = new File( "F:\\文档\\java\\008Spring\\图片\\ioc_aop\\Maven或者Gradle下载.png" );
        ImageHolder Thumbnail = new ImageHolder( new FileInputStream( thumbnail ), "AOP优势.png" );
        ImageHolder productImg1 = new ImageHolder( new FileInputStream( img1 ), "bean作用域.png" );
        ImageHolder productImg2 = new ImageHolder( new FileInputStream( img2 ), "Maven或者Gradle下载.png" );
        List<ImageHolder> productImgs = new LinkedList<>();
        productImgs.add( productImg1 );
        productImgs.add( productImg2 );
        productService.modifyProduct( product, Thumbnail, productImgs );*/
    }
}