package cn.lw.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/6/20
 */
public class CodeUtil {
    public static boolean CheckVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY
        );
        String verifyCodeActual = HttpServletUtil.getString( request,"verifyCodeActual" );
        if (verifyCodeActual == null || !verifyCodeActual.toUpperCase().equals( verifyCodeExpected )) {
            return false;
        }
        return true;

    }
}
