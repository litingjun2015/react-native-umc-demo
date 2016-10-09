package com.reactnative_init;

/**
 * Created by duanglink on 9/30/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import cn.richinfo.umcsdk.activity.UMCMainActivity;

public class UMCModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public UMCModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "UMCAndroid";
    }

    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message+" (call from redefined umc native module)", duration).show();

        Intent intent = new Intent(getCurrentActivity(), UMCMainActivity.class);
        getCurrentActivity().startActivity(intent);
    }

    /**
     * 从JS页面跳转到Activity界面，并且等待从Activity返回的数据给JS
     * @param className
     * @param successBack
     * @param errorBack
     */
//    @ReactMethod
//    public void startActivityFromJSGetResult(String className, int requestCode, Callback successBack, Callback errorBack){
//        try {
//            Activity currentActivity=getCurrentActivity();
//            if(currentActivity!=null) {
//                Class toActivity = Class.forName(className);
//                Intent intent = new Intent(currentActivity, UMCMainActivity.class);
//                currentActivity.startActivityForResult(intent, requestCode);
//                //进行回调数据
//                successBack.invoke(MainActivity.mQueue.take());
//            }
//        } catch (Exception e) {
//            errorBack.invoke(e.getMessage());
//            e.printStackTrace();
//        }
//
//    }


    /**
     * 从JS页面跳转到Activity界面，并且等待从Activity返回的数据给JS
     * @param className
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void startActivityFromJSGetResult(String className,int requestCode, Callback successBack, Callback errorBack){
        try {
            Activity currentActivity=getCurrentActivity();
            if(currentActivity!=null) {
//                Class toActivity = Class.forName(className);
                Intent intent = new Intent(currentActivity,ThreeActivity.class);
                currentActivity.startActivityForResult(intent,requestCode);
                //进行回调数据
                successBack.invoke(MainActivity.mQueue.take());
            }
        } catch (Exception e) {
            errorBack.invoke(e.getMessage());
            e.printStackTrace();
        }

    }


}