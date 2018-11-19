package qzy.com.toolslibrary.utils;

import android.os.Environment;


import java.io.File;

import qzy.com.toolslibrary.base.Constants;

/**
 * 文件操作类
 *
 * @author Smile Wei
 * @since 2014/4/4
 */
public class FileUtil {
    /**
     * 获取SD卡的路径
     *
     * @return sd卡路径
     */
    public static String getSDCardBasePath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取图片缓存的路径
     *
     * @return 图片缓存file
     */
    public static File getCacheFolder() {
        String path = FileUtil.getSDCardBasePath()
                + Constants.CACHE_FILE_PATH;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


}
