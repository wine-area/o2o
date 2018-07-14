package cn.lw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/10
 */

@Getter
@Setter
@ToString
public class WechatUser implements Serializable {
    public static final long sericalVersionUID = -42546456423165L;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty
    private String province;

    @JsonProperty
    private String city;

    @JsonProperty
    private String country;

    @JsonProperty("headimgurl")
    private String headImgUrl;

    @JsonProperty
    private String language;

    @JsonProperty
    private String[] privilege;
    @JsonProperty
    private int sex;

    public String  getSex() {
        if (sex == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * country : 中国
     * province : 四川
     * city : 南充
     * openid : o7f1W0q3YV4sIDBHJT-LrpxpUi-I
     * sex : 1
     * nickname : 李文
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/eQ5UViaPGMC5eXe2o17YZYBG8l24wCzHRorBv32RsjOyZTF6EytGQJnZsx3qCJhlRdckcE0dibNedsu68aDMz8Vw/132
     * language : zh_CN
     * privilege : []
     */
}
