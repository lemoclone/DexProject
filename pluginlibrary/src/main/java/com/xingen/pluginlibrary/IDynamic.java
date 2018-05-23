package com.xingen.pluginlibrary;

import android.content.Context;

/**
 * Author by ${xinGen},  Date on 2018/5/23.
 */
public interface IDynamic {
   /**
    * 涉及插件中回调
    * @param callBack
    */
   void  invokeCallback(CallBack callBack);

   /**
    * 加载插件中的resource
    * @param context
    */
   void invokeResource(Context context);
}
