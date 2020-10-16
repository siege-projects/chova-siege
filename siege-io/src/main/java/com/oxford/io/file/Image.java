package com.oxford.io.file;

import org.apache.commons.lang3.StringUtils;

/**
 * 图片相关处理类
 *
 * @author Chova
 * @date 2020/7/9
 */
public class Image {

    /**
     * 根据路径名称，判断当前路径文件是否为图片
     *
     * @param pathName 路径名称
     * @return boolean 是否为图片
     */
    public static boolean isImage(String pathName) {
        boolean isImage = false;
        if (StringUtils.isNotEmpty(pathName)) {
            pathName = pathName.trim().toLowerCase();
            boolean imageType = pathName.endsWith("jpg")
                    || pathName.endsWith("jpeg")
                    || pathName.endsWith("png")
                    || pathName.endsWith("bmp")
                    || pathName.endsWith("gif");
            if (imageType) {
                isImage = true;
            }
        }
        return isImage;
    }
}
