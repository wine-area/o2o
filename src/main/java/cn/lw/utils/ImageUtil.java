package cn.lw.utils;

import cn.lw.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/6/18
 */
public class ImageUtil {

    private static final String basePath=Thread.currentThread()
            .getContextClassLoader().getResource( "" ).getPath();
    public static final SimpleDateFormat dateFormat=new SimpleDateFormat( "yyyyMMddHHmmss" );
    public static final Random random = new Random();
    private static Logger logger = LoggerFactory.getLogger( ImageUtil.class );

    /**
     * 将commonsMultipartFile转换为File
     * @param commonsMultipartFile
     * @return
     */
    public static File transferCommonsMultiPartFileToFile(CommonsMultipartFile commonsMultipartFile){
        File file = new File(commonsMultipartFile.getOriginalFilename());
        try {
            commonsMultipartFile.transferTo(file  );
        } catch (IOException e) {
            e.printStackTrace();
            logger.error( e.toString() );
        }
        return file;
    }
    /**
     * 处理缩略图并返回 新生成图片的相对路径
     * @param
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr)  {
        String realFileName=getRandomFileName();
        String extension = getFileExtension( thumbnail.getFileName() );
        makeDirPath( targetAddr );
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug( "Current relativeAddr is" +realFileName );
        File dest = new File( PathUtil.getImageBasePath() + relativeAddr );
       logger.error( basePath );
        logger.debug( "Current completeAddr is"+PathUtil.getImageBasePath()+relativeAddr );
        try {
            Thumbnails.of( thumbnail.getImage() ).size( 200, 200 )
                    .watermark( Positions.BOTTOM_RIGHT,
                            ImageIO.read( new File( basePath + "/water.png" ) ), 0.25f )
                    .outputQuality( 0.8f ).toFile( dest );
        } catch (IOException e) {
            logger.error( e.toString() );

            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及的路径
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
        File dirPath = new File( realFileParentPath );
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring( fileName.lastIndexOf( "." ) );
    }

    /**
     * 生产随机文件名
     * @return
     */
    public static String getRandomFileName() {
        int ranNum = random.nextInt(89999)+10000;
        String nowDateStr = dateFormat.format( new Date() );
        return nowDateStr + ranNum;
    }

    /**
     * 先判断shopPath是文件还是路径
     * 文件则直接删除
     * 路径则删除改路径下的所有文件
     * @param shopPath
     */
    public static void deleteFileOrPath(String shopPath) {
        File fileOrPath = new File( PathUtil.getImageBasePath() + shopPath );
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static String generateNormalImg(ImageHolder productImg, String target) {

        String realFileName=getRandomFileName();
        String extension = getFileExtension( productImg.getFileName() );
        makeDirPath( target );
        String relativeAddr = target + realFileName + extension;
        logger.debug( "Current relativeAddr is" +realFileName );
        File dest = new File( PathUtil.getImageBasePath() + relativeAddr );
        logger.error( basePath );
        logger.debug( "Current completeAddr is"+PathUtil.getImageBasePath()+relativeAddr );
        try {
            Thumbnails.of( productImg.getImage() ).size( 337, 640 )
                    .watermark( Positions.BOTTOM_RIGHT,
                            ImageIO.read( new File( basePath + "/water.png" ) ), 0.25f )
                    .outputQuality( 0.9f ).toFile( dest );
        } catch (IOException e) {
            logger.error( e.toString() );

            e.printStackTrace();
        }
        return relativeAddr;
    }
}
