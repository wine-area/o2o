package cn.lw.web.shopadmin;

import cn.lw.domain.*;
import cn.lw.dto.ImageHolder;
import cn.lw.dto.ShopExecution;
import cn.lw.enums.ShopStateEnum;
import cn.lw.services.IAreaService;
import cn.lw.services.IProductCategoryService;
import cn.lw.services.IShopCategoryService;
import cn.lw.services.IShopOperationService;
import cn.lw.utils.CodeUtil;
import cn.lw.utils.HttpServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.shopadmin
 * @date 2018/6/18
 */
@Controller
@RequestMapping("shopadmin")
public class ShopManagerController {
    @Autowired
    private IShopOperationService shopService;
    @Autowired
    //我就用这个试下
    private IShopCategoryService shopCategoryService;
    @Autowired
    private IAreaService areaService;


    @RequestMapping(value = "getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();

        try {
            shopCategoryList = shopCategoryService.selectAll( new ShopCategory() );
            areaList = areaService.selectAll();
            modelMap.put( "shopCategoryList", shopCategoryList );
            modelMap.put( "areaList", areaList );
            modelMap.put( "success", true );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.CheckVerifyCode( request )) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "验证码错误" );
            return modelMap;
        }
        //1.接受并转换相应的信息,包括店铺信息,和店铺图片
        String shopStr = HttpServletUtil.getString( request, "shopStr" );
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue( shopStr, Shop.class );
        } catch (IOException e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.toString() );
            e.printStackTrace();
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext() );
        if (commonsMultipartResolver.isMultipart( request )) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile( "shopImg" );
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "上传图片为空" );
            return modelMap;
        }
        //2.注册店铺
        if (shop != null) {
            /*PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");*/
            PersonInfo owner = new PersonInfo();
            owner.setUserId( 1 );
            shop.setOwner( owner );
            ShopExecution se = null;
            try {
                if (shopImg == null) {
                    se = shopService.modifyShop( shop, null );
                } else {
                    ImageHolder imageHolder = new ImageHolder( shopImg.getInputStream(), shopImg.getOriginalFilename() );
                    se = shopService.insert( shop,imageHolder );
                }
            } catch (IOException e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", se.getStateInfo() );
                return modelMap;
            } catch (Error error) {
                System.out.println( error.getMessage() );
            }
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put( "success", true );
                //从session
               /* List<Shop> shopList = (List<Shop>) request.getSession().getAttribute( "shopList" );
                if (shopList.isEmpty()) {
                    shopList = new LinkedList<>();
                }
                shopList.add( shop );
                request.getSession().setAttribute( "shopList",shopList );*/
            } else {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", se.getStateInfo() );
            }
            return modelMap;
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "请输入店铺信息" );
            return modelMap;
        }

    }

    @RequestMapping(value = "getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int shopId = HttpServletUtil.getInt( request, "shopId" );
        if (shopId > -1) {
            try {
                Shop shop = shopService.selectByPrimaryKey( shopId );
                List<Area> areaList = areaService.selectAll();
                modelMap.put( "success", true );
                modelMap.put( "shop", shop );
                modelMap.put( "areaList", areaList );
            } catch (Exception e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
            }
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "empty shopId" );
        }
        return modelMap;
    }

    @RequestMapping(value = "modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (!CodeUtil.CheckVerifyCode( request )) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "验证码错误" );
            return modelMap;
        }
        //1.接受并转换相应的信息,包括店铺信息,和店铺图片
        String shopStr = HttpServletUtil.getString( request, "shopStr" );
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue( shopStr, Shop.class );
        } catch (IOException e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.toString() );
            e.printStackTrace();
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext() );
        if (commonsMultipartResolver.isMultipart( request )) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile( "shopImg" );
        }
        //2.修改店铺
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se = null;
            try {
                if (shopImg == null) {
                    se = shopService.modifyShop( shop,  null );
                } else {
                    ImageHolder imageHolder = new ImageHolder( shopImg.getInputStream(), shopImg.getOriginalFilename() );
                    se = shopService.modifyShop( shop, imageHolder );
                }
            } catch (IOException e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", se.getStateInfo() );
                return modelMap;
            }
            if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                modelMap.put( "success", true );
            } else {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", se.getStateInfo() );
            }
            return modelMap;
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "请输入店铺Id" );
            return modelMap;
        }

    }

    @RequestMapping(value = "getshoplist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId( 1 );//模拟
        request.getSession().setAttribute( "user", user );
        user = (PersonInfo) request.getSession().getAttribute( "user" );
        try {
            Shop shopConditon = new Shop();
            shopConditon.setOwner( user );
            ShopExecution se = shopService.queryShopList( shopConditon, 0, 100 );
            modelMap.put( "success", true );
            modelMap.put( "user", user );
            modelMap.put( "shopList", se.getShopList() );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
        }
        return modelMap;
    }

    @RequestMapping(value = "getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int shodId = HttpServletUtil.getInt( request, "shopId" );
        if (shodId <= 0) {
            Object currentShop = request.getSession().getAttribute( "currentShop" );
            if (currentShop == null) {
                modelMap.put( "redirect", true );
                modelMap.put( "url", "/shop/shoplist" );
            } else {
                Shop shop = (Shop) currentShop;
                modelMap.put( "redirect", false );
                modelMap.put( "shopId", shop.getShopId() );
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId( shodId );
            request.getSession().setAttribute( "currentShop", currentShop );
            modelMap.put( "redirect", false );
        }
        return modelMap;
    }

}
