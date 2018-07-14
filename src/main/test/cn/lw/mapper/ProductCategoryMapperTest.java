package cn.lw.mapper;

import cn.lw.domain.ProductCategory;
import cn.lw.services.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/2
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryMapperTest extends BaseTest {
    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Test
    public void testBselectAllByShopId() {
        List<ProductCategory> productCategories = productCategoryMapper.selectAllByShopId( 28 );
        productCategories.forEach( (i)-> System.out.println(i) );
    }

    @Test
    public void testAbatchInsertProductCategory(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryName( "商品类别批量测试"+i );
            productCategory.setCreateTime( new Date() );
            productCategory.setPriority( i );
            productCategory.setShopId( 28 + i );
            productCategoryList.add( productCategory );
        }
        productCategoryMapper.batchInsertProductCategory( productCategoryList );
    }

    @Test
    public void testCDelete() {


        for (int i = 0; i < 5; i++) {
           productCategoryMapper.deleteProductCategory( 28 + i, 28 + i );
        }
    }
}