package cn.lw.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.frontend
 * @date 2018/7/7
 */
@Controller@RequestMapping("frontend")
public class FrontendController {
    @GetMapping("index")
    private String index(){
        return "frontend/index";
    }

    @GetMapping("shoplist")
    private String shopList() {
        return "frontend/shoplist";
    }

    @GetMapping("shopdetail")
    private String shopDetail() {
        return "frontend/shopdetail";
    }

    @GetMapping("productdetail")
    private String productDetail() {
        return "frontend/productdetail";
    }
}
