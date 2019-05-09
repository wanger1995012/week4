package com.bawei.week1;

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
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //设置fresco的缓存大小及路径
        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this)
                .setMaxCacheSize(100*ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(60*ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(20*ByteConstants.MB)
                .setBaseDirectoryPath(this.getApplicationContext().getCacheDir())
                .setBaseDirectoryName("com.bw.fresco")
                .build();
        ImagePipelineConfig builder=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,builder);
    }
}
