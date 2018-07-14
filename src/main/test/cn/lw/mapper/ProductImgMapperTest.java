package cn.lw.mapper;

import cn.lw.domain.ProductImg;
import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/7/4
 */
public class ProductImgMapperTest extends BaseTest {

    @Autowired
    ProductImgMapper productImgMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        productImgMapper.selectAll().forEach( (i)-> System.out.println(i) );
    }

    @Test
    public void batchInsertProductImg() {
        List<ProductImg> productImgs = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            ProductImg productImg = new ProductImg();
            productImg.setCreateTime( new Date() );
            productImg.setImgAddr( "测试" + i );
            productImg.setImgDesc( "测试描述" + i );
            productImg.setPriority( i );
            productImg.setProductId( i+1 );
            productImgs.add( productImg );
        }
        productImgMapper.batchInsertProductImg( productImgs );
    }
}