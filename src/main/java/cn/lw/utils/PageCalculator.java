package cn.lw.utils;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/6/24
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex-1)>0?(pageIndex-1)*pageSize:0;
    }
}
