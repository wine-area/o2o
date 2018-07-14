package cn.lw.web.shopadmin;

import cn.lw.domain.ProductCategory;
import cn.lw.domain.Shop;
import cn.lw.dto.ProductCategoryExecution;
import cn.lw.dto.Result;
import cn.lw.enums.ProductCategoryStateEnum;
import cn.lw.services.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web
 * @date 2018/7/3
 */
@Controller
@RequestMapping("shopadmin")
public class productCategoryManagementContrroller {
    @Autowired
    private IProductCategoryService productCategoryService;

    @RequestMapping(value = "getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductcategoryList(HttpServletRequest request) {
        //TODO
        Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.selectAllByShopId( currentShop.getShopId() );
            return new Result<>( true, productCategoryList );
        } else {
            ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>( false,
                    productCategoryStateEnum.getStateInfo(),
                    productCategoryStateEnum.getState() );
        }

    }

    @RequestMapping(value = "addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addShopCategoryList(@RequestBody List<ProductCategory> productCategories,
                                                    HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
        productCategories.forEach( (i) -> i.setShopId( currentShop.getShopId() ) );
        if (productCategories != null && productCategories.size() > 0) {
            try {
                ProductCategoryExecution productCategoryExecution =
                        productCategoryService.batchInsertProductCategory( productCategories );
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put( "success", true );
                } else {
                    modelMap.put( "success", false );
                    modelMap.put( "errMsg", productCategoryExecution.getStateInfo() );
                }
            } catch (Exception e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
            }
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", ProductCategoryStateEnum.EMPTY_LIST.getStateInfo() );
        }
        return modelMap;
    }

    @RequestMapping(value = "removeproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeShopCategory(Integer productCategoryId,
                                                   HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
                int shopId = currentShop.getShopId();
                ProductCategoryExecution productCategoryExecution = productCategoryService.
                        deleteProductCategory( productCategoryId, shopId );
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put( "success", true );
                } else {
                    modelMap.put( "success", false );
                    modelMap.put( "errMsg", productCategoryExecution.getStateInfo() );
                }
            } catch (Exception e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
            }
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "请至少选择一个商品类别" );
        }
        return modelMap;
    }
}
