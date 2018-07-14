package cn.lw.services.Impl;
import cn.lw.domain.Area;
import cn.lw.domain.PersonInfo;
import cn.lw.domain.Shop;
import cn.lw.domain.ShopCategory;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ShopExecution;
import cn.lw.enums.ShopStateEnum;
import cn.lw.services.BaseTest;
import cn.lw.services.IShopOperationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.services.Impl
 * @date 2018/6/18
 */
public class ShopServiceImplTest extends BaseTest{
    @Autowired
    private IShopOperationService shopService;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() throws FileNotFoundException {
      /*  Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory ShopCategory=new ShopCategory();
        owner.setUserId( 1 );
        area.setAreaId( 2 );
        ShopCategory.setShopCategoryId( 1 );
        shop.setOwner( owner );
        shop.setArea( area );
        shop.setPriority( 1 );
        shop.setShopCategory( ShopCategory );
        shop.setShopDesc( "test8" );
        shop.setShopAddr( "test8" );
        shop.setShopName( "test8" );
        shop.setEnableStatus( ShopStateEnum.CHECK.getState() );
        shop.setPhone( "test8" );
        shop.setAdvice( "审核中8" );
        File file=new File("F:\\文档\\java\\网站用到的图片集_wosn.net\\item\\headtitle\\2017061320400198256.jpg");
        ImageHolder imageHolder = new ImageHolder(new FileInputStream( file ),file.getName());
        ShopExecution execution = shopService.insert( shop, imageHolder);
        System.out.println(execution);*/

    }

    @Test
    public void selectByPrimaryKey() {
        Shop shop = shopService.selectByPrimaryKey( 28 );
        System.out.println( shop );
    }

    @Test
    public void selectAll() {
    }

    @Test//测试方法在这
    public void updateByPrimaryKey() throws FileNotFoundException {
        Shop shop = shopService.selectByPrimaryKey( 1 );
        shop.setShopName("测试2");
        ShopExecution shopExecution = shopService.modifyShop( shop, null );
        System.out.println( shopExecution );
    }

    @Test
    public void testqueryShopList() {
        Shop shop = new Shop();
        shop.setShopName( "大" );
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId( 1 );
        shop.setOwner( personInfo );
        ShopExecution shopExecution = shopService.queryShopList( shop, 1, 5 );
        System.out.println( shopExecution );

    }
}