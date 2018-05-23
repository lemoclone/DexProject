package com.xingen.plugin;

import android.content.Context;

import com.xingen.pluginlibrary.BeanProvider;
import com.xingen.pluginlibrary.CallBack;
import com.xingen.pluginlibrary.IDynamic;

/**
 * Author by ${xinGen},  Date on 2018/5/23.
 */
public class Dynamic implements IDynamic {
    @Override
    public void invokeCallback(CallBack callBack) {
        BeanProvider provider=new Bean();
        provider.setName("根根");
        callBack.callback(provider);
    }
    @Override
    public void invokeResource(Context context) {

    }
}
