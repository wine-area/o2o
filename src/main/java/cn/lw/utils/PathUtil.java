package cn.lw.utils;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/6/18
 */
public class PathUtil {
    public static final String seperator = System.getProperty( "file.separator" );
    public static String getImageBasePath(){
        String os = System.getProperty( "os.name" );
        String basePath;
        if (os.toLowerCase().startsWith( "win" )){
            basePath = "D:/projectdev/upload";
        }else {
            basePath = "/home/upload";
        }
        basePath.replace( "/", seperator );
        return basePath;
    }
    public static String getShopImgagePath(long shopId){
        String imagePath = "/images/items/shop/"+shopId+"/";
        return imagePath.replace( "/", seperator );
    }
}
