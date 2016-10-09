package cn.richinfo.umcsdk.activity;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import cm.pass.sdk.account.UserInfo;
import cm.pass.sdk.auth.AuthnHelper;
import cm.pass.sdk.auth.TokenListener;
import cn.richinfo.umcsdk.show.R;
import cn.richinfo.umcsdk.util.Constant;
import cn.richinfo.umcsdk.util.StringFormat;

import static android.R.attr.duration;

@TargetApi(23)
@SuppressLint("HandlerLeak")
public class UMCMainActivity extends Activity implements View.OnClickListener {

	protected String TAG = "UMCMainActivity";
	private static final int RESULT = 0x111;
	private Context mContext;
	private String mResultString;
	private TextView mResultText;

	private TokenListener mListener;
	private EditText mMobileEdit;

	/** 获取用户资料所需要的信息，可以用shareprefence保存，同时返回的还有有效期，注意处理 */
	private String mAccessToken;
	private String mUniqueId;
	private AuthnHelper mAuthnHelper;
	/** 是否使用测试线 */
	private boolean isTest = false;

	private ResultDialog mResultDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		AuthnHelper.setTest(isTest);
		// 设置对应的appid与appkey
		// Constant.isTest(isTest);
		/** 获得 AuthnHelper实例 */
		mAuthnHelper = AuthnHelper.getInstance(mContext);
		init();

		//Toast.makeText(this, "init() ok", Toast.LENGTH_SHORT).show();
	}

	private void init() {
		setContentView(R.layout.umc_activity_main);
		findViewById(R.id.sms_login).setOnClickListener(this);
		findViewById(R.id.outh_login).setOnClickListener(this);
		findViewById(R.id.get_user_info).setOnClickListener(this);
		findViewById(R.id.logout_Authorization).setOnClickListener(this);
		findViewById(R.id.mobilenumberStatus).setOnClickListener(this);
		findViewById(R.id.verifyMobile).setOnClickListener(this);
		mResultDialog = new ResultDialog(mContext);
		// 服务端地址切换
		RadioGroup cb = (RadioGroup) this.findViewById(R.id.umcsdk_istest);
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.umcsdk_test) {
					AuthnHelper.getInstance(mContext).setTestModel(1);
					Constant.isTest(true);

				} else if (checkedId == R.id.umcsdk_grey) {
					AuthnHelper.getInstance(mContext).setTestModel(2);
					Constant.isTest(false);

				} else if (checkedId == R.id.umcsdk_product) {
					AuthnHelper.getInstance(mContext).setTestModel(3);
					Constant.isTest(false);

				} else {
				}
			}
		});

		// appid切换
		RadioGroup clientIdSwitch = (RadioGroup) this
				.findViewById(R.id.umcsdk_is_authorize);
		clientIdSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.umcsdk_normal) {
							Constant.isAuthorize(false);
							mAuthnHelper.logOut();
							mAccessToken = "";

						} else if (checkedId == R.id.umcsdk_authorize) {
							Constant.isAuthorize(true);
							mAuthnHelper.logOut();
							mAccessToken = "";

						}
					}
				});


		// 默认选项
		RadioButton rb = (RadioButton) findViewById(R.id.umcsdk_normal);
		rb.setChecked(true);

		mResultText = (TextView) findViewById(R.id.text_result);
		mListener = new TokenListener() {
			@Override
			public void onGetTokenComplete(JSONObject jObj) {
				mResultString = jObj.toString();

				//mQueue.add(mResultString);

				mHandler.sendEmptyMessage(RESULT);
				if (jObj != null && jObj.has("uniqueid")) {
					mUniqueId = jObj.optString("uniqueid");
					mAccessToken = jObj.optString("accessToken");
				}
			}
		};


		//设置高级权限
		Constant.isAuthorize(true);
		//mAuthnHelper.logOut();
		//mAccessToken = "";

		//displayLogin();

		Intent mIntent=new Intent();
		mIntent.putExtra("three_result","From Activity的数据回调过来啦~");
		UMCMainActivity.this.setResult(Activity.RESULT_OK,mIntent);
		UMCMainActivity.this.finish();
	}




	@Override
	public void onClick(View v) {
		Log.e("开始请求sdk验证:", "开始请求sdk鉴权:");
		mResultText.setText("开始请求...");
		int i = v.getId();
		if (i == R.id.sms_login) {
			displayLogin();

		} else if (i == R.id.outh_login) {
			oAuthLogin();

		} else if (i == R.id.get_user_info) {
			getUserInfo();

		} else if (i == R.id.logout_Authorization) {
			logOut();

		} else if (i == R.id.mobilenumberStatus) {
			findStatusByMobileNumber();

		} else if (i == R.id.verifyMobile) {
			verityMobile();

		} else {
		}
	}

	protected void getAccesstokenByUserinfo() {
		List<UserInfo> accounts = mAuthnHelper.getAccounts();
		UserInfo user = null;
		if (accounts != null && accounts.size() > 0) {
			user = accounts.get(accounts.size() - 1);
		}
		// 测试时默认用最后登录的账户登录
		mAuthnHelper.getAccessTokenByUserInfo(Constant.APP_ID,
				Constant.APP_KEY, user, mListener);
	}

	private void verityMobile() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		mMobileEdit = new EditText(this);
		mMobileEdit.setHint("请输入手机号码");
		mMobileEdit.setText("");
		builder.setView(mMobileEdit);
		builder.setPositiveButton(android.R.string.ok,
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						mAuthnHelper.silentVerifyMobile(Constant.APP_ID,
								Constant.APP_KEY, mMobileEdit.getText()
										.toString().trim(), mVerifyListener);
					}
				});
		builder.setNeutralButton(android.R.string.cancel, null);
		builder.create().dismiss();
		builder.create().show();
	}

	private TokenListener mVerifyListener = new TokenListener() {
		@Override
		public void onGetTokenComplete(JSONObject jsonobj) {
			mResultString = jsonobj.toString();
			mHandler.sendEmptyMessage(RESULT);
		}
	};
	UserInfo userinfo;

	private void displayLogin() {
		mAuthnHelper.umcLoginByType(Constant.APP_ID, Constant.APP_KEY,
				AuthnHelper.UMC_LOGIN_DISPLAY, mListener);
	}


	private void oAuthLogin() {
		mAuthnHelper.umcLoginByType(Constant.APP_ID, Constant.APP_KEY,
				AuthnHelper.UMC_LOGIN_OAUTH, mListener);
	}

	private void logOut() {
		mAuthnHelper.logOut();
		mAccessToken = "";
		mResultString = "清除数据成功!";
		mHandler.sendEmptyMessage(RESULT);

	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case RESULT:
				mResultText.setText(mResultString);
				mResultDialog.setResult(StringFormat
						.logcatFormat(mResultString));



				//getUserInfo();


				break;
			default:
				break;
			}
		}

	};



	/*********************************** 以下为获取用户资料相关的接口 ***********************************/
	private void getUserInfo() {
		mAuthnHelper.getUserInfo(Constant.APP_ID, mAccessToken, mUniqueId,
				mListener);
	}

	private void findStatusByMobileNumber() {
		mAuthnHelper.getStatusByMobileNumber(Constant.APP_ID, mAccessToken,
				mUniqueId, mListener);
	}

}
