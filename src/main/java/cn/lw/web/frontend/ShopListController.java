package cn.lw.web.frontend;

import cn.lw.domain.Area;
import cn.lw.domain.Shop;
import cn.lw.domain.ShopCategory;
import cn.lw.dto.ShopExecution;
import cn.lw.mapper.AreaMapper;
import cn.lw.mapper.ShopCategoryMapper;
import cn.lw.mapper.ShopMapper;
import cn.lw.services.IAreaService;
import cn.lw.services.IShopCategoryService;
import cn.lw.services.IShopOperationService;
import cn.lw.services.Impl.AreaServiceImpl;
import cn.lw.services.Impl.ShopCategoryServiceImpl;
import cn.lw.utils.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.frontend
 * @date 2018/7/7
 */
@Controller
@RequestMapping("frontend")
public class ShopListController {
    @Autowired
    IAreaService areaService;
    @Autowired
    IShopCategoryService shopCategoryService;
    @Autowired
    IShopOperationService shopOperationService;

    @GetMapping("listshoppageinfo")
    @ResponseBody
    private Map<String, Object> listShopPageInfo(HttpServletRequest request) {
        Map<String, Object> modelmap = new HashMap<>();
        int parentId = HttpServletUtil.getInt( request, "parentId" );
        List<ShopCategory> shopCategoryList;
        if (parentId > 0) {
            try {
                ShopCategory shopCategory = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId( parentId );
                shopCategory.setParent( parent );
                shopCategoryList = shopCategoryService.selectAll( shopCategory );
            } catch (Exception e) {
                modelmap.put( "success", false );
                modelmap.put( "errMsg", e.getMessage() );
                return modelmap;
            }
        } else {
            try {
                shopCategoryList = shopCategoryService.selectAll( null );
            } catch (Exception e) {
                modelmap.put( "success", false );
                modelmap.put( "errMsg", e.getMessage() );
                return modelmap;
            }
        }
        modelmap.put( "shopCategoryList", shopCategoryList );
        List<Area> areaList;
        try {
            areaList = areaService.selectAll();
            modelmap.put( "areaList", areaList );
        } catch (Exception e) {
            modelmap.put( "success", false );
            modelmap.put( "errMsg", e.getMessage() );
            return modelmap;
        }
        modelmap.put( "success", true );
        return modelmap;
    }

    @GetMapping("listshops")
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelmap = new HashMap<>();
        int pageIndex = HttpServletUtil.getInt( request, "pageIndex" );
        int pageSize = HttpServletUtil.getInt( request, "pageSize" );
        if (pageIndex > -1 && pageSize > 0) {
            int parentId = HttpServletUtil.getInt( request, "parentId" );
            String shopName = HttpServletUtil.getString( request, "shopName" );
            int areaId = HttpServletUtil.getInt( request, "areaId" );
            int shopCategoryId = HttpServletUtil.getInt( request, "shopCategoryId" );
            Shop shop4Conditoin = compactShop4Conditoin( shopName, shopCategoryId, areaId, parentId );
            ShopExecution shopExecution =
                    shopOperationService.queryShopList( shop4Conditoin, pageIndex, pageSize );
            modelmap.put( "shopList", shopExecution.getShopList() );
            modelmap.put( "count", shopExecution.getCount() );
            modelmap.put( "success", true );
        } else {
            modelmap.put( "success", false );
            modelmap.put( "errMsg", "empty pageIndex Or pageSize" );
        }
        return modelmap;
    }

    private Shop compactShop4Conditoin(String shopName, int shopCategoryId, int areaId, int parentId) {
        Shop shopCondition = new Shop();
        if (shopName != null) {
            shopCondition.setShopName( shopName );
        }
        ShopCategory shopCategory = new ShopCategory();
        if (shopCategoryId > 0) {
            shopCategory.setShopCategoryId( shopCategoryId );
        }
        if (parentId > 0) {
            ShopCategory parent = new ShopCategory();
            parent.setShopCategoryId( parentId );
            shopCategory.setParent( parent );
        }
        shopCondition.setShopCategory( shopCategory );
        if (areaId > 0) {
            Area area = new Area();
            area.setAreaId( areaId );
            shopCondition.setArea( area );
        }
        shopCondition.setEnableStatus( 1 );
        return shopCondition;
    }
}
