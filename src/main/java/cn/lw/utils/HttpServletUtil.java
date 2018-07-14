package cn.lw.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/6/18
 */
public class HttpServletUtil {
    public static int getInt(HttpServletRequest request,String key){
        try {
            return Integer.decode( request.getParameter( key ) );
        }catch (Exception e){
            return -1;
        }

    }
    public static Long getLong(HttpServletRequest request,String key){
        try {
            return Long.valueOf( request.getParameter( key ) );
        }catch (Exception e){
            return -1l;
        }
    }
    public static Double getDouble(HttpServletRequest request,String key){
        try {
            return Double.valueOf( request.getParameter( key ) );
        }catch (Exception e){
            return -1d;
        }
    }
    public static boolean getBoolean(HttpServletRequest request,String key){
        try {
            return Boolean.valueOf( request.getParameter( key ) );
        }catch (Exception e){
            return false;
        }
    }
    public static String getString(HttpServletRequest request,String key){
        try {
            String result = request.getParameter( key );
            if (result != null)
                result = result.trim();
            if ("".equals( result )) {
                return null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
