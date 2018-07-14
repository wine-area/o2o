package cn.lw.web.frontend;

import cn.lw.domain.HeadLine;
import cn.lw.domain.ShopCategory;
import cn.lw.mapper.HeadLineMapper;
import cn.lw.mapper.ShopCategoryMapper;
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
@Controller
@RequestMapping("frontend")
public class MainPageController {
    @Autowired
    HeadLineMapper headLineMapper;
    @Autowired
    ShopCategoryMapper shopCategoryMapper;


    @GetMapping("listmainpageinfo")
    @ResponseBody
    private Map<String, Object> listMainPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<ShopCategory> shopCategoryList = shopCategoryMapper.selectAll( null );
            modelMap.put( "shopCategoryList", shopCategoryList );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        try {
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus( 1 );
            List<HeadLine> headLineList = headLineMapper.queryHeadLine( headLineCondition );
            modelMap.put( "headLineList", headLineList );
        } catch (Exception e) {
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
        modelMap.put( "success", true );
        return modelMap;
    }
}
