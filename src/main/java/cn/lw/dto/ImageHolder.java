package cn.lw.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * @author lw
 * @version 1.0
 * @description cn.lw.dto
 * @date 2018/7/4
 */
@Getter@Setter
public class ImageHolder {
    private InputStream image;
    private String fileName;

    public ImageHolder(InputStream image, String fileName) {
        this.image = image;
        this.fileName = fileName;
    }
}
