package com.reactnative_init;

/**
 * Created by duanglink on 9/30/16.
 */

import android.content.Intent;
import android.widget.Toast;

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
}