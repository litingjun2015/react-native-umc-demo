package cn.richinfo.umcsdk;

import android.app.Application;

public class MyApplication extends Application {

	String TAG = "MyApplication";

	public static String umcUID = "";
	public static String umcPassId = "";

	public void onCreate() {
		super.onCreate();
	}
}
