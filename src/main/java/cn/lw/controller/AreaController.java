package cn.lw.controller;

import cn.lw.domain.Area;
import cn.lw.services.IAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.controller
 * @date 2018/6/17
 */

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger( AreaController.class );
    @Autowired
    private IAreaService areaService;
    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info( "====start====" );
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areas = new ArrayList<>();
        try{
            areas=areaService.selectAll();
            modelMap.put( "rows", areas );
            modelMap.put( "total", areas.size() );
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.toString() );
        }
        logger.info( "====END====" );
        return modelMap;
    }
}
