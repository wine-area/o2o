package cn.lw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/10
 */
@Getter@Setter
public class UserAccessToken {
    /**
     * 获取到的凭证
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * 凭证有效时间
     */
    @JsonProperty("expires_in")
    private int expiresIn;
    /**
     * 表示更新令牌,用来获取下一次的访问令牌
     */
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("openid")
    private String  openId;
    /**
     * 表示权限范围
     */
    @JsonProperty
    private String scope;
}
