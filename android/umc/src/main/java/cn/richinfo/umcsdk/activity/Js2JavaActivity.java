package cn.richinfo.umcsdk.activity;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cm.pass.sdk.auth.AuthnHelper;
import cm.pass.sdk.auth.TokenListener;
import cn.richinfo.umcsdk.show.R;
import cn.richinfo.umcsdk.util.Constant;

@SuppressLint("JavascriptInterface")
public class Js2JavaActivity extends Activity {
	private WebView contentWebView = null;
	private TextView msgView = null;
	private TokenListener mListener;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.js_main);
		contentWebView = (WebView) findViewById(R.id.webview);
		msgView = (TextView) findViewById(R.id.msg);
		Log.e("Js2JavaActivity:",
				"this.getCallingActivity():" + this.getCallingActivity());

		// 启用javascript
		contentWebView.getSettings().setJavaScriptEnabled(true);
		// 从assets目录下面的加载html
		contentWebView.loadUrl("file:///android_asset/wst.html");

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(btnClickListener);
		contentWebView.addJavascriptInterface(this, "wst");
	}

	OnClickListener btnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			// 无参数调用
			contentWebView.loadUrl("javascript:javacalljs()");
			// 传递参数调用
			contentWebView.loadUrl("javascript:javacalljswithargs("
					+ "'hello world'" + ")");
		}
	};

	@JavascriptInterface
	public void startFunction() {
		Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				msgView.setText(msgView.getText() + "\njs调用了java函数");

			}
		});
	}

	@JavascriptInterface
	public void startFunction(final String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" + str);

			}
		});
	}

	/**
	 * 
	 * @param wabpsub
	 *            计费参数 类似： ServiceId@@ Subcode@@ TransactionId@@ UserId@@
	 *            ChannelId
	 */
	@JavascriptInterface
	public void orderFunction(String wabpsub) {
		/** 结果回调函数 */
		mListener = new TokenListener() {
			@Override
			public void onGetTokenComplete(final JSONObject arg0) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						msgView.setText("调用了订购接口：" + arg0.toString());

					}
				});
			}
		};
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				AuthnHelper
						.getInstance(Js2JavaActivity.this)
						.getAuthnOrder(
								Constant.APP_ID,
								Constant.APP_KEY,
								"fmimm@@-1182343444@@15012721396059@@MTg2MTgyODgyOTk=@@20809@@ireader@@118.192.170.16@@24000",
								mListener);
			}

		});
	}
}