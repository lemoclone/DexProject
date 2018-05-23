package com.xingen.dexclassloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xingen.pluginlibrary.BeanProvider;
import com.xingen.pluginlibrary.CallBack;
import com.xingen.pluginlibrary.IDynamic;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String dexPath;
    private String fileName = "plugin-debug.apk";
    private String cacheDir;
    private DexClassLoader dexClassLoader;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        this.dexPath = Utils.copyFiles(newBase, fileName);
        this.cacheDir = Utils.getCacheDir(newBase).getAbsolutePath();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dexClassLoader = new DexClassLoader(dexPath, cacheDir, null, getClassLoader());
        findViewById(R.id.main_test).setOnClickListener(this);
        loadImage();
    }

    /**
     * 加载插件中的resource资源
     */
    private void loadImage(){
        ImageView imageView=findViewById(R.id.main_iv);
        File file = new File(dexPath);
        Log.d("xingen", "file exist " + file.exists()+" "+dexPath);
       Resources resources=AssertsDexLoader.getResource(getApplicationContext(),dexPath);

        try {
            Drawable drawable=resources.getDrawable(resources.getIdentifier("bd_logo1","drawable","com.xingen.plugin"));
           imageView.setImageDrawable(drawable);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            Class<?> mClass = dexClassLoader.loadClass("com.xingen.plugin.Dynamic");
            IDynamic iDynamic = (IDynamic) mClass.newInstance();
            iDynamic.invokeCallback(new CallBack() {
                @Override
                public void callback(BeanProvider beanProvider) {
                    //插件回调宿主
                    Toast.makeText(getApplicationContext()," 插件回调宿主 ，获取的Bean实体的字段是 "+beanProvider.getName(),Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
