package cn.lw.mapper;

import cn.lw.domain.PersonInfo;
import cn.lw.domain.Shop;
import cn.lw.domain.ShopCategory;
import cn.lw.services.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.mapper
 * @date 2018/6/24
 */
public class ShopMapperTest extends BaseTest {

    @Autowired
    ShopMapper shopMapper;
    @Test
    public void queryShopList() {
        Shop shop = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId( 12 );
        shopCategory.setParent( parent );
        shop.setShopCategory( shopCategory );
        List<Shop> shopList = shopMapper.queryShopList( shop, 0, 5 );
        shopList.forEach( (i)-> System.out.println(i) );
    }

    @Test
    public void queryShopCount() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId( 1 );
        shop.setOwner( personInfo );
        System.out.println( shopMapper.queryShopCount( shop ) );
    }
}