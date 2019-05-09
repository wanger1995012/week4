package com.bawei.week2;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("com.bw.fresco")
                .setBaseDirectoryPath(this.getApplicationContext().getCacheDir())
                .setMaxCacheSize(100*ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(60*ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(20*ByteConstants.MB)
                .build();
        ImagePipelineConfig builder=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,builder);
    }
}
