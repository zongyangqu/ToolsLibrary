package qzy.com.toolslibrary.widget.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import qzy.com.toolslibrary.utils.FileUtil;

/**
 * @author Smile Wei
 * @since 2016/2/25.
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        /*if (!Glide.()) {
            GlideBuilder gb = new GlideBuilder();
            DiskCache cache = DiskLruCacheWrapper.get(FileUtil.getCacheFolder(), 1000 * 1024 * 1024);
            gb.setDiskCache(cache);
            Glide.setup(gb);
        }*/
    }

    public void registerComponents(Context context, Glide glide) {
    }
}
