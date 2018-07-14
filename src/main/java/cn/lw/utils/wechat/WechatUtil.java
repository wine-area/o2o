package cn.lw.utils.wechat;

import cn.lw.domain.PersonInfo;
import cn.lw.dto.UserAccessToken;
import cn.lw.dto.WechatUser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils.wechat
 * @date 2018/7/10
 */
public class WechatUtil {
    private static Logger log = LoggerFactory.getLogger( WechatUtil.class );

    /**
     * 获取UserAccessToken实体类
     * @param code
     * @return
     * @throws IOException
     */
    public static UserAccessToken getUserAccessToken(String code)
    throws IOException {
        /**
         * Appid
         */
        String appId = "wx028a3c33de3bb338";
        log.debug( "appId:"+appId );
        String appSecret = "26fe03f76cff46df9c62819b0ac6172e";
        log.debug( "appSerret:" + appSecret );
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"" +
                "&secret="+appSecret+"&code="+code+"&grant_type=" +
                "authorization_code";
        String tokenStr = httpsRequset( url, "GET", null );
        log.debug( "userAccessToken:" + tokenStr );
        UserAccessToken userAccessToken = new UserAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userAccessToken = objectMapper.readValue( tokenStr, UserAccessToken.class );
        } catch (JsonParseException e) {
            log.error( "获取用户accessToken失败" + e.getMessage() );
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.error( "获取用户accessToken失败" + e.getMessage() );
            e.printStackTrace();
        } catch (IOException e) {
            log.error( "获取用户accessToken失败" + e.getMessage() );
            e.printStackTrace();
        }
        if (userAccessToken == null) {
            log.error(  "获取用户accessToken失败");
            return null;
        }
        return userAccessToken;
    }

    /**
     * 获取WechatUser实体类
     * @param accessToken
     * @param openId
     * @return
     */
    public static WechatUser getUserInfo(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
                + "&lang=zh_CN";
        String userStr = httpsRequset( url, "GET", null );
        log.debug( "user info:" + userStr );
        WechatUser user = new WechatUser();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            user = objectMapper.readValue( userStr, WechatUser.class );
        } catch (JsonParseException e) {
            log.error( "获取用户信息失败:" + e.getMessage() );
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.error( "获取用户信息失败:" + e.getMessage() );
            e.printStackTrace();
        } catch (IOException e) {
            log.error( "获取用户信息失败:" + e.getMessage() );
            e.printStackTrace();
        }
        if (user == null) {
            return null;
        }
        return user;
    }
    private static String httpsRequset(String requestUrl, String requestMethod, String outPutStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            /**
             * 创建SSLContext对象,并使用我们指定的信任管理器初始化
             */
            TrustManager[] trustManagers = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance( "SSL", "SunJSSE" );
            sslContext.init( null, trustManagers, new SecureRandom() );
            /**
             * 从上述的SSLContext对象获得SSLSocketFactory对象
             */
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL( requestUrl );
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setSSLSocketFactory( ssf );
            httpURLConnection.setDoInput( true );
            httpURLConnection.setDoInput( true );
            httpURLConnection.setUseCaches( false );
            //设置请求方式
            httpURLConnection.setRequestMethod( requestMethod );
            if ("GET".equalsIgnoreCase( requestMethod )) {
                httpURLConnection.connect();
            }
            if (null != outPutStr) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write( outPutStr.getBytes( "UTF-8" ) );
                outputStream.close();
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader( inputStream, "UTF-8" );
            BufferedReader bufferedReader = new BufferedReader( reader );
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append( str );
            }
            bufferedReader.close();
            reader.close();
            inputStream.close();
            inputStream = null;
            httpURLConnection.disconnect();
            log.debug( "https buffer:" + buffer.toString() );

        } catch (ConnectException e) {
            log.error( "Weixin server connection time out" );
        } catch (Exception e) {
            log.error( "https request error:{}",e );
        }
        return buffer.toString();
    }
    public static PersonInfo getPersonInfo(WechatUser user) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName( user.getNickName() );
        personInfo.setGender( user.getSex() );
        personInfo.setEnableStatus( 1 );
        personInfo.setProfileImg( user.getHeadImgUrl() );
        return personInfo;
    }
}
