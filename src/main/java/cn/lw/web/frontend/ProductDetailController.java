package cn.lw.web.frontend;

import cn.lw.domain.Product;
import cn.lw.services.IProductService;
import cn.lw.utils.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.frontend
 * @date 2018/7/8
 */
@Controller@RequestMapping("frontend")
public class ProductDetailController {
    @Autowired
    private IProductService productService;

    @GetMapping("listproductdetailpageinfo")
    @ResponseBody
    private Map<String, Object> listproductdetailpageinfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int productId = HttpServletUtil.getInt( request, "productId" );
        if (productId > 0) {
            Product product = productService.selectByPrimaryKey( productId );
            modelMap.put( "success", true );
            modelMap.put( "product", product );
        } else {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "empty productID" );
        }
        return modelMap;
    }
}
