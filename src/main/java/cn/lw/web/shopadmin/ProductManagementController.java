package cn.lw.web.shopadmin;

import cn.lw.domain.Product;
import cn.lw.domain.ProductCategory;
import cn.lw.domain.Shop;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ProductExecution;
import cn.lw.enums.ProductStateEnum;
import cn.lw.exceptions.ProductCategoryOperationException;
import cn.lw.services.IProductCategoryService;
import cn.lw.services.IProductService;
import cn.lw.utils.CodeUtil;
import cn.lw.utils.HttpServletUtil;
import cn.lw.utils.PageCalculator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.shopadmin
 * @date 2018/7/4
 */
@Controller
@RequestMapping("shopadmin")
public class ProductManagementController {
    @Autowired
    IProductService productService;

    @Autowired
    IProductCategoryService productCategoryService;

    public static final int IMAGE_MAX_COUNT = 6;

    @PostMapping("addproduct")
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.CheckVerifyCode( request )) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "验证码错误" );
            return modelMap;
        }
        String productStr = HttpServletUtil.getString( request, "productStr" );
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgs = new LinkedList<>();
        //从session中获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext() );
        try {
            if (multipartResolver.isMultipart( request )) {
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile)
                        multipartHttpServletRequest.getFile( "thumbnail" );
                thumbnail = new ImageHolder( thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename() );
                for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
                    CommonsMultipartFile productImgFile =
                            (CommonsMultipartFile) multipartHttpServletRequest.getFile( "productImg" + i );
                    if (productImgFile != null) {
                        productImgs.add( new ImageHolder( productImgFile.getInputStream(),
                                productImgFile.getOriginalFilename() ) );
                    } else {
                        break;
                    }
                }
            } else {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", "上传图片不能为空" );
                return modelMap;
            }
        } catch (IOException e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        try {
            product = objectMapper.readValue( productStr, Product.class );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        if (product != null && thumbnail != null && productImgs != null) {
            Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
            Shop shop = new Shop();
            shop.setShopId( currentShop.getShopId() );
            product.setShop( shop );
            try {
                ProductExecution productExecution = productService.insert( product, thumbnail, productImgs );
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put( "success", true );
                } else {
                    modelMap.put( "success", false );
                    modelMap.put( "errMsg", productExecution.getStateInfo() );
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
                return modelMap;
            }
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "请输入商品信息" );
        }
        return modelMap;
    }

    @GetMapping("getproduct/{productId}")
    @ResponseBody
    private Map<String, Object> getProductById(@PathVariable int productId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productId > 0) {
            Product product = productService.selectByPrimaryKey( productId );
            List<ProductCategory> productCategories = productCategoryService
                    .selectAllByShopId( product.getShop().getShopId() );
            modelMap.put( "success", true );
            modelMap.put( "product", product );
            modelMap.put( "productCategoryList", productCategories );
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "empty product id" );
        }
        return modelMap;
    }

    @PostMapping("modifyproduct")
    @ResponseBody
    private Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        boolean statusChange = HttpServletUtil.getBoolean( request, "statusChange" );
        if (statusChange && CodeUtil.CheckVerifyCode( request )) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "验证码错误" );
            return modelMap;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Product product;
        ImageHolder thumbnail=null;
        List<ImageHolder> productImgs = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext() );
        try {
            if (multipartResolver.isMultipart( request )) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile)
                        multipartHttpServletRequest.getFile( "thumbnail" );
                if (thumbnailFile != null) {
                    thumbnail = new ImageHolder( thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename() );
                }
                for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
                    CommonsMultipartFile productImg= (CommonsMultipartFile)
                            multipartHttpServletRequest.getFile( "productImg" + i );
                    if (productImg != null) {
                        productImgs.add( new ImageHolder( productImg.getInputStream(), productImg.getOriginalFilename() ) );
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        try {
            String productStr = HttpServletUtil.getString( request, "productStr" );
            product=objectMapper.readValue( productStr, Product.class );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        if (product != null) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
                product.setShop( currentShop );
                ProductExecution productExecution = productService.modifyProduct( product, thumbnail, productImgs );
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put( "success", true );
                } else {
                    modelMap.put( "success", false );
                    modelMap.put( "errMsg", productExecution.getStateInfo() );
                }
            } catch (RuntimeException e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
                return modelMap;
            }
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "商品信息不能为空" );
        }
        return modelMap;
    }
 @GetMapping("getproductlistbyshop")
    @ResponseBody
    private Map<String, Object> getProducts(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
     int pageIndex = HttpServletUtil.getInt( request, "pageindex" );
     int pageSize = HttpServletUtil.getInt( request, "pagesize" );
     ObjectMapper objectMapper = new ObjectMapper();
     Shop currentShop = (Shop) request.getSession().getAttribute( "currentShop" );
        if (pageIndex>-1&&pageSize>-1&&currentShop != null && currentShop.getShopId()!=null) {

            int productCategoryId = HttpServletUtil.getInt( request, "productCategoryId" );
            String productName = HttpServletUtil.getString( request, "productName" );
            Product productCondition = compactProductCondition( productName, productCategoryId, currentShop.getShopId() );
            ProductExecution productExecution = productService.queryProductList( productCondition,
                    PageCalculator.calculateRowIndex( pageIndex, pageSize ), pageSize );

            modelMap.put( "success", true );
            modelMap.put( "count", productExecution.getCount() );
            modelMap.put( "productList", productExecution.getProducts() );

        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "查询失败,查询信息有误" );
        }
        return modelMap;
    }

    private Product compactProductCondition(String productName, int productCategoryId, Integer shopId) {
        Product product = new Product();
        if (productName != null) {
            product.setProductName( productName );
        }
        if (productCategoryId > -1) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId( productCategoryId );
            product.setProductCategory( productCategory );
        }
        if (shopId != null && shopId > -1) {
            Shop shop = new Shop();
            shop.setShopId( shopId );
            product.setShop( shop );
        }
        return product;
    }
}
