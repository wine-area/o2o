package cn.lw.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.utils
 * @date 2018/7/14
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    //需要加密的属性

    public static final String[] encryptPropNames = {"username", "password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncyrptProp( propertyName )) {
            String decryptValue = DESUtil.getDecryptString( propertyValue );
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    private boolean isEncyrptProp(String propertyName) {
        boolean isEncry=false;
        for (String propName : encryptPropNames) {
            if (propertyName.equals( propName )) {
                isEncry = true;
            }
        }
        return isEncry;
    }
}
