package cn.lw.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.Introspector;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.shopadmin
 * @date 2018/6/19
 */
@Controller@RequestMapping(value="shopadmin",method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping("shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
    @RequestMapping("shoplist")
    public String shopList  (){
        return "shop/shoplist";
    }

    @RequestMapping("shopmanagement")
    public String shopManagement  (){
        return "shop/shopmanagement";
    }

    @RequestMapping("productcategorylist")
    public String productCategoryList  (){
        return "shop/productcategorylist";
    }

    @RequestMapping("productoperation")
    public String productOperation  (){
        return "shop/productoperation";
    }

    @GetMapping("productmanagement")
    public  String productManagement(){
        return "shop/productmanagement";
    }
}
