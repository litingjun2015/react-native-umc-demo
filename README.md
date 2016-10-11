# 使用

```
git clone
npm install
react-native run-android
react-native run-ios
```

# 集成步骤

关于中国移动统一认证： [http://dev.10086.cn/wiki/?p5_01_01](http://dev.10086.cn/wiki/?p5_01_01)

# 1. 集成安卓SDK
（tongyirenzheng sdk_outer_v1.4.0 for Android_0818160907092935）

## 1.1 转换Eclipse工程
参考：[Import and Convert an Eclipse Project into Android Studio](http://www.appstoremarketresearch.com/articles/android-studio-tutorial-convert-eclipse-project/)

![](http://upload-images.jianshu.io/upload_images/3167321-39978baa996214c9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-b60db7d13932321c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


转换后有2个错误，手动解决。
![](http://upload-images.jianshu.io/upload_images/3167321-efc1ace50f4a0561.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3167321-7730d73c27076e17.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> BUILD SUCCESSFUL
Total time: 10.56 secs


在手机上运行测试：（注意高级权限才能获取用户手机号）
![](http://upload-images.jianshu.io/upload_images/3167321-8550957346013a0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-bfdd00cb5d6d7d0b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-33101fae046a9b71.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 1.2 将Android工程转换成Android library project供其他工程引用


参考：[Convert existing project to library project in Android Studio](http://stackoverflow.com/questions/17614250/convert-existing-project-to-library-project-in-android-studio)

> applicationId "cn.richinfo.umcsdk.show"

> /Users/duanglink/AndroidStudioProjects/umcsdk-open-demo-androidstudio/app/build.gradle
Warning:(9, 26) Not targeting the latest versions of Android; compatibility modes apply. Consider testing and updating this version. Consult the android.os.Build.VERSION_CODES javadoc for details.
Warning:(10, 5) Not all execution paths return a value
Warning:(18, 1) Not all execution paths return a value
Warning:(21, 13) A newer version of com.android.support:support-v4 than 18.0.0 is available: 24.2.0

解决：error: constant expression required case R.id.umcsdk_test:
参考：[what causes "constant expression required" errors for the generated R ](http://stackoverflow.com/questions/21005205/what-causes-constant-expression-required-errors-for-the-generated-r-id-xxx-val)

> /Users/duanglink/AndroidStudioProjects/umcsdk-open-demo-androidstudio/app/src/main/java/cn/richinfo/umcsdk/activity/MainActivity.java
Warning:(93, 7) 'else' statement has empty body
Warning:(128, 9) Condition 'jObj != null' is always 'true'
Warning:(159, 5) 'else' statement has empty body

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-c628a8c316479d10.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> Library project resource identifiers are not constant static final int
s, just static int s.
Convert the code that needs to switch on library resource ids to if
-else structures.
Further reading: [http://tools.android.com/tips/non-constant-fields](http://tools.android.com/tips/non-constant-fields)


测试：
> 参考：[actionbarsherlock - How do I add a library project to Android Studio](http://stackoverflow.com/questions/16588064/how-do-i-add-a-library-project-to-android-studio)


> 11:31:36 AM Please change caller according to com.intellij.openapi.project.IndexNotReadyException documentation: Please change caller according to com.intellij.openapi.project.IndexNotReadyException documentation



> /Users/duanglink/AndroidStudioProjects/TestCases/app/src/main/AndroidManifest.xml:7:9-43 Error:
	Attribute application@icon value=(@mipmap/ic_launcher) from AndroidManifest.xml:7:9-43
	is also present at [TestCases:umc:unspecified] AndroidManifest.xml:21:9-45 value=(@drawable/ic_launcher).
	Suggestion: add 'tools:replace="android:icon"' to <application> element at AndroidManifest.xml:5:5-18:19 to override.

> /Users/duanglink/AndroidStudioProjects/TestCases/app/src/main/AndroidManifest.xml:10:9-40 Error:
	Attribute application@theme value=(@style/AppTheme) from AndroidManifest.xml:10:9-40
	is also present at [TestCases:umc:unspecified] AndroidManifest.xml:23:9-68 value=(@android:style/Theme.Holo.Light.NoActionBar).
	Suggestion: add 'tools:replace="android:theme"' to <application> element at AndroidManifest.xml:5:5-18:19 to override.

在 /app/src/main/AndroidManifest.xml 添加：tools:replace="android:icon,android:theme"


- File from non-active changelist if modified
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-0b5d0c6a5829ebff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> There was a open bug in IDEA regarding this issue:

> IDEA-51396 Tasks: if conflicted file is opened in 2 editor tabs, tasks toolbar actions (switch changelist/move changes/ignore) don't make toolbar disappear
It's the IDE behaviours and you don't have to worry about it at all.

Choose what options suits you the best and continue your work.

- [Starting Another Activity | Android Developers](https://developer.android.com/training/basics/firstapp/starting-activity.html)

直接这样调用会报错：
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-a998fc685b1cf399.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> E/AndroidRuntime: FATAL EXCEPTION: main
                  Process: com.example.duanglink.testcases, PID: 28612
                  java.lang.NoSuchFieldError: No static field sms_login of type I in class Lcn/richinfo/umcsdk/show/R$id; or its superclasses (declaration of 'cn.richinfo.umcsdk.show.R$id' appears in /data/data/com.example.duanglink.testcases/files/instant-run/dex/slice-slice_1-classes.dex)
                      at cn.richinfo.umcsdk.activity.UMCMainActivity.init(UMCMainActivity.java:70)
                      at cn.richinfo.umcsdk.activity.UMCMainActivity.onCreate(UMCMainActivity.java:65)
                      at android.app.Activity.performCreate(Activity.java:5939)

参考：[NoSuchFieldError: No static field listView1 of type I in class](http://stackoverflow.com/questions/18347460/why-getting-nosuchfiledexception-when-using-another-android-project-as-a-library)

> The library and the child project has the same layout name : activity_main. Rename either of them solves the problem.


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-dafcdfd0264771c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-f80bafcf06e9a590.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

备份：umc.zip

## 1.3 React Native调原生模块

参考：[Native Modules – React Native | A framework for building native apps](http://facebook.github.io/react-native/docs/native-modules-android.html)

注意重载：
> java.lang.IllegalStateException: Native module ToastModule tried to override ToastModule for module name ToastAndroid. If this was your intention, return true from ToastModule#canOverrideExistingModule()

> 声明一个NativeModule类时，需要重写canOverrideExistingModule()及getName()两个方法，在getName中返回的字符串即为在JS中引用的类，即在JS中使用PushHelper来引用PushHelperModule这个类。

> 文／KenChoi（简书作者）
原文链接：http://www.jianshu.com/p/1925d84d0f5c
著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。

## 1.4 React Native调统一认证SDK

> 参考：
[React Native 进阶之原生混合与数据通信开发详解-适配Android开发](http://www.lcode.org/react-native-%E8%BF%9B%E9%98%B6%E4%B9%8B%E5%8E%9F%E7%94%9F%E6%B7%B7%E5%90%88%E4%B8%8E%E6%95%B0%E6%8D%AE%E9%80%9A%E4%BF%A1%E5%BC%80%E5%8F%91%E8%AF%A6/)


完美支持～

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-0240b2ac2a94d9ce.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-1879ffd86b8992a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 升级有风险，需谨慎：

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-3c06a32b3fb51a3b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> DuangLink-MacBook-Pro:react-native-umc-demo duanglink$ react-native run-android
Starting JS server...
Running /usr/local/opt/android-sdk/platform-tools/adb -s 155556db reverse tcp:8081 tcp:8081
Building and installing the app on the device (cd android && ./gradlew installDebug...
Starting a new Gradle Daemon for this build (subsequent builds will be faster).

> FAILURE: Build failed with an exception.
* Where:
Build file '/Users/duanglink/ReactNativeProjects/react-native-umc-demo/android/app/build.gradle' line: 1

> * What went wrong:
A problem occurred evaluating project ':app'.
> java.lang.UnsupportedClassVersionError: com/android/build/gradle/AppPlugin : Unsupported major.minor version 52.0

> * Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

> BUILD FAILED

暂时不升级。



# 2. 集成iOS SDK

## 2.1 React Native 调用iOS原生模块

> 参考：[Native Modules – React Native - Facebook Code](https://facebook.github.io/react-native/docs/native-modules-ios.html)

> 参考：统一认证-对外开放开发指南_iOS_v1.4.0-0817（公开版）.docx

**2016-10-09 10:39:26.068 reactnative_init[276:54000] App Transport Security has blocked a cleartext HTTP (http://) resource load since it is insecure. Temporary exceptions can be configured via your app's Info.plist file.**

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-6dd0456fc3e19293.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




支持：

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-06778adbbbd31141.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-9407e9d64f2329ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 2.2 React Native 访问iOS原生模块的数据

> 参考：[React Native进阶*原生*模块封装特性篇详解-适配*iOS*开发 ](http://www.lcode.org/react-native%E8%BF%9B%E9%98%B6%E4%B9%8B%E5%8E%9F%E7%94%9F%E6%A8%A1%E5%9D%97%E5%B0%81%E8%A3%85%E7%89%B9%E6%80%A7%E7%AF%87%E8%AF%A6%E8%A7%A3-%E9%80%82/)





# 3. 发布