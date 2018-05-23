package com.xingen.dexclassloader;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Author by ${xinGen},  Date on 2018/5/23.
 */
public class AssertsDexLoader {
    /**
     * 获取指定apk的AssetManager
     *
     * 例如：获取插件的AssetManager
     *
     * @param apkPath
     * @return
     */
    public static AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            AssetManager.class.getDeclaredMethod("addAssetPath", String.class).invoke(
                    assetManager, apkPath);
            return assetManager;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    /**
     * 获取到插件中的Resource
     * @param context
     * @param apkPath
     * @return
     */
    public static Resources getResource(Context context, String apkPath){
        AssetManager assetManager = createAssetManager(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

}
