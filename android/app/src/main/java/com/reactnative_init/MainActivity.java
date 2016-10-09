package com.reactnative_init;

import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.ReactActivity;

import java.util.concurrent.ArrayBlockingQueue;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "reactnative_init";
    }


    //构建一个阻塞的单一数据的队列
    public static ArrayBlockingQueue<String> mQueue = new ArrayBlockingQueue<String>(1);

    /**
     * 打开 带返回的Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == 200) {
//            String result = data.getStringExtra("three_result");
//            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
//            if (result != null && !result.equals("")) {
//                mQueue.add(result);
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//            } else {
//                mQueue.add("无数据啦");
//                Toast.makeText(this, "无数据啦", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            mQueue.add("没有回调...");
//            Toast.makeText(this, "没有回调...", Toast.LENGTH_SHORT).show();
//        }
//    }


    /**
     * 打开 带返回的Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            String result = data.getStringExtra("three_result");
            if (result != null && !result.equals("")) {
                mQueue.add(result);
            } else {
                mQueue.add("无数据啦");
            }
        } else {
            mQueue.add("没有回调...");
        }
    }
}
