package cn.lw.web.frontend;

import cn.lw.domain.Product;
import cn.lw.domain.ProductCategory;
import cn.lw.domain.Shop;
import cn.lw.dto.ProductExecution;
import cn.lw.services.IProductCategoryService;
import cn.lw.services.IProductService;
import cn.lw.services.IShopOperationService;
import cn.lw.utils.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.frontend
 * @date 2018/7/7
 */
@Controller@RequestMapping("frontend")
public class ShopDetailController {
    @Autowired
    private IShopOperationService shopOperationService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping("listshopdetailpageinfo")
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int shopId = HttpServletUtil.getInt( request, "shopId" );
        Shop shop;
        List<ProductCategory> productCategoryList;
        if (shopId > 0) {
            shop = shopOperationService.selectByPrimaryKey( shopId );
            productCategoryList = productCategoryService.selectAllByShopId( shopId );
            modelMap.put( "shop", shop );
            modelMap.put( "productCategoryList", productCategoryList );
            modelMap.put( "success", false );
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "empty Shop Id" );
        }
        return modelMap;
    }

    @GetMapping("listproductsbyshop")
    @ResponseBody
    private Map<String, Object> listproductsbyshop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletUtil.getInt( request, "pageIndex" );
        int pageSize = HttpServletUtil.getInt( request, "pageSize" );
        int shopId = HttpServletUtil.getInt( request, "shopId" );
        if (pageIndex > -1 && pageSize > 0 && shopId > 0) {
            int productCategoryId = HttpServletUtil.getInt( request, "productCategoryId" );
            String productName = HttpServletUtil.getString( request, "productName" );
            Product productCondition = compactProductCondition4Search( productName, productCategoryId, shopId );
            ProductExecution productExecution = productService
                    .queryProductList( productCondition, pageIndex, pageSize );
            modelMap.put( "success", true );
            modelMap.put( "count", productExecution.getCount() );
            modelMap.put( "productList", productExecution.getProducts() );
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "empty shopId or pageSize or pageIndex" );
        }
        return modelMap;
    }

    private Product compactProductCondition4Search(String productName, int productCategoryId, int shopId) {
        Product productCondition = new Product();
        if (productName != null) {
            productCondition.setProductName( productName );
        }
        if (productCategoryId > 0) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId( productCategoryId );
            productCondition.setProductCategory( productCategory );
        }
        if (shopId > 0) {
            Shop shop = new Shop();
            shop.setShopId( shopId );
            productCondition.setShop( shop );
        }
        productCondition.setEnableStatus( 1 );
        return productCondition;
    }
}
