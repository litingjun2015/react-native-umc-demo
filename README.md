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

BUILD SUCCESSFUL
Total time: 10.56 secs


在手机上运行测试：（注意高级权限才能获取用户手机号）
![](http://upload-images.jianshu.io/upload_images/3167321-8550957346013a0e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-bfdd00cb5d6d7d0b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-33101fae046a9b71.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 1.2 将Android工程转换成Android library project供其他工程引用

参考：[Convert existing project to library project in Android Studio](http://stackoverflow.com/questions/17614250/convert-existing-project-to-library-project-in-android-studio)

applicationId "cn.richinfo.umcsdk.show"

Information:BUILD SUCCESSFUL
Information:Total time: 4.109 secs

/Users/duanglink/AndroidStudioProjects/umcsdk-open-demo-androidstudio/app/build.gradle
Warning:(9, 26) Not targeting the latest versions of Android; compatibility modes apply. Consider testing and updating this version. Consult the android.os.Build.VERSION_CODES javadoc for details.
Warning:(10, 5) Not all execution paths return a value
Warning:(18, 1) Not all execution paths return a value
Warning:(21, 13) A newer version of com.android.support:support-v4 than 18.0.0 is available: 24.2.0

解决：error: constant expression required case R.id.umcsdk_test:
参考：[what causes "constant expression required" errors for the generated R ](http://stackoverflow.com/questions/21005205/what-causes-constant-expression-required-errors-for-the-generated-r-id-xxx-val)

/Users/duanglink/AndroidStudioProjects/umcsdk-open-demo-androidstudio/app/src/main/java/cn/richinfo/umcsdk/activity/MainActivity.java
Warning:(93, 7) 'else' statement has empty body
Warning:(128, 9) Condition 'jObj != null' is always 'true'
Warning:(159, 5) 'else' statement has empty body

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-c628a8c316479d10.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Library project resource identifiers are not constant static final int
s, just static int s.
Convert the code that needs to switch on library resource ids to if
-else structures.
Further reading: [http://tools.android.com/tips/non-constant-fields](http://tools.android.com/tips/non-constant-fields)

测试：

参考：[actionbarsherlock - How do I add a library project to Android Studio](http://stackoverflow.com/questions/16588064/how-do-i-add-a-library-project-to-android-studio)

11:31:36 AM Please change caller according to com.intellij.openapi.project.IndexNotReadyException documentation: Please change caller according to com.intellij.openapi.project.IndexNotReadyException documentation



/Users/duanglink/AndroidStudioProjects/TestCases/app/src/main/AndroidManifest.xml:7:9-43 Error:
	Attribute application@icon value=(@mipmap/ic_launcher) from AndroidManifest.xml:7:9-43
	is also present at [TestCases:umc:unspecified] AndroidManifest.xml:21:9-45 value=(@drawable/ic_launcher).
	Suggestion: add 'tools:replace="android:icon"' to <application> element at AndroidManifest.xml:5:5-18:19 to override.

/Users/duanglink/AndroidStudioProjects/TestCases/app/src/main/AndroidManifest.xml:10:9-40 Error:
	Attribute application@theme value=(@style/AppTheme) from AndroidManifest.xml:10:9-40
	is also present at [TestCases:umc:unspecified] AndroidManifest.xml:23:9-68 value=(@android:style/Theme.Holo.Light.NoActionBar).
	Suggestion: add 'tools:replace="android:theme"' to <application> element at AndroidManifest.xml:5:5-18:19 to override.

在 /app/src/main/AndroidManifest.xml 添加：tools:replace="android:icon,android:theme"


- File from non-active changelist if modified
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-0b5d0c6a5829ebff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

There was a open bug in IDEA regarding this issue:

IDEA-51396 Tasks: if conflicted file is opened in 2 editor tabs, tasks toolbar actions (switch changelist/move changes/ignore) don't make toolbar disappear
It's the IDE behaviours and you don't have to worry about it at all.

Choose what options suits you the best and continue your work.

- [Starting Another Activity | Android Developers](https://developer.android.com/training/basics/firstapp/starting-activity.html)

直接这样调用会报错：
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-a998fc685b1cf399.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

E/AndroidRuntime: FATAL EXCEPTION: main
                  Process: com.example.duanglink.testcases, PID: 28612
                  java.lang.NoSuchFieldError: No static field sms_login of type I in class Lcn/richinfo/umcsdk/show/R$id; or its superclasses (declaration of 'cn.richinfo.umcsdk.show.R$id' appears in /data/data/com.example.duanglink.testcases/files/instant-run/dex/slice-slice_1-classes.dex)
                      at cn.richinfo.umcsdk.activity.UMCMainActivity.init(UMCMainActivity.java:70)
                      at cn.richinfo.umcsdk.activity.UMCMainActivity.onCreate(UMCMainActivity.java:65)
                      at android.app.Activity.performCreate(Activity.java:5939)

参考：[NoSuchFieldError: No static field listView1 of type I in class](http://stackoverflow.com/questions/18347460/why-getting-nosuchfiledexception-when-using-another-android-project-as-a-library)


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-dafcdfd0264771c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/3167321-f80bafcf06e9a590.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

完美支持～

备份：umc.zip

## 1.3 React Native调原生模块
## 1.4 React Native调统一认证SDK 

# 2. 集成iOS SDK
# 3. 发布