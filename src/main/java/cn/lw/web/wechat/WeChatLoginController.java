package cn.lw.web.wechat;

import cn.lw.domain.PersonInfo;
import cn.lw.domain.WechatAuth;
import cn.lw.dto.UserAccessToken;
import cn.lw.dto.WechatAuthExecution;
import cn.lw.dto.WechatUser;
import cn.lw.enums.WechatAuthStateEnum;
import cn.lw.services.IPersonInfoService;
import cn.lw.services.IWechatAuthService;
import cn.lw.utils.wechat.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author lw
 * @version 1.0
 * @description cn.lw.web.wechat
 * @date 2018/7/10
 */
@Controller
@RequestMapping("wechatlogin")
public class WeChatLoginController {
    private static Logger logger = LoggerFactory.getLogger( WeChatLoginController.class );
    public static final String FRONTEND = "1";
    public static final String SHOPEND = "2";

    @Autowired
    private IWechatAuthService wechatAuthService;
    @Autowired
    private IPersonInfoService personInfoService;
    @GetMapping("logincheck")
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.debug( "weixin login get..." );
        //从微信公众号传来的code 可以拿到access_token,从而获取到用户的信息
        String code = request.getParameter( "code" );


        String roleType = request.getParameter( "state" );

        WechatUser user = null;
        WechatAuth auth = null;
        String openId;
        if (null != code) {
            UserAccessToken token;
            try {
                //通过code获取UserAccessToken
                token = WechatUtil.getUserAccessToken( code );
                logger.debug( "weixin login token:" + token.toString() );
                String accessToken = token.getAccessToken();
                openId = token.getOpenId();
                user = WechatUtil.getUserInfo( accessToken, openId );
                auth = wechatAuthService.queryWechatAuthByOpenId( openId );
                logger.debug( "weixin login user:" + user.toString() );
                request.getSession().setAttribute( "openId", openId );
            } catch (IOException e) {
                logger.error( "error in getUserAccessToken or getUserInfo or findByOpenId"
                        + e.getMessage() );
                e.printStackTrace();
            }
        }
        /**
         * TODO
         * 获取openId后取数据库判断改微信账号是否已在本网站注册,如果已注册,直接登陆
         * 否则注册,实现微信与本网站无缝对接
         */


        if (auth == null) {
            auth=new WechatAuth();
            auth.setOpenId( user.getOpenId() );
            auth.setCreateTime( new Date() );
            PersonInfo personInfo = WechatUtil.getPersonInfo( user );
            if (FRONTEND.equals( roleType )) {
                personInfo.setUserType( 1 );
            } else {
                personInfo.setUserType( 2 );
            }
            auth.setUser( personInfo );
            WechatAuthExecution wechatAuthExecution = wechatAuthService.register( auth );
            if (wechatAuthExecution.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                return null;
            } else {
                PersonInfo  user2session= personInfoService.getPersonInfoById( auth.getUser().getUserId() );
                request.getSession().setAttribute( "user",user2session );
            }
        }
        if (FRONTEND.equals( roleType )) {
            return "frontend/index";
        } else {
            return "shopadmin/shoplist";
        }



           /*
           *  if (user != null) {
                return "frontend/index";
            } else {
                return null;
            }*/
    }


}
