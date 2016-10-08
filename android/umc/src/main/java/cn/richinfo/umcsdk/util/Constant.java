package cn.richinfo.umcsdk.util;

/**
 * @Title:Constant.java
 * @Description:
 * @Copyright: Copyright (c) 2013
 * @Company:深圳彩讯科技有限公司
 * @author licq 2014年7月1日
 * @version 1.0
 */
public class Constant {

	public static String REDIRECT_URL = "http://dev.10086.cn";

	public static String APP_ID = "300009228352"; // 猪猪侠百变飞车
	public static String APP_KEY = "440591DD9C69F864A2645F7217CC4590";

	public static void isTest(boolean flag) {
		// 测试线
		if (flag) {
			// 普通
			// APP_ID = "300005578175";
			// APP_KEY = "89F81AA676AC2C14926F5F5D02BC30EA";

			// 高级权限AP
			APP_ID = "300005124474";
			APP_KEY = "A73EBF94D34B9D77761BDF24BEDEDFAE";

			// 特别AP
			// APP_ID = "300005578174";
			// APP_KEY = "E691FEF78462FDA3E4400F3BE81906BF";
			// 现网
		} else {
			// APP_ID = "300009137663";// 现网 新捕猎游戏
			// APP_KEY = "9A788A9396D2C7AB4EE3077725DF003C";

			APP_ID = "300008880753";// 现网 欢度春节2 有高级授权
			APP_KEY = "9BB253542D429931AFA8F4D9C013808B";

			// APP_ID = "300009153741";// 现网 新的测试1 无高级授权
			// APP_KEY = "EF6C3DC692EF0DA2803FE0BC3766C60B";

			// APP_ID = "300009228352"; // 猪猪侠百变飞车
			// APP_KEY = "440591DD9C69F864A2645F7217CC4590";

			// APP_ID = "300008925958"; // 消灭星星3D
			// APP_KEY = "CE11C24299BAF37B7A0FF226104ED3B1";

			// APP_ID = "300009153744";// 现网 新的测试2
			// APP_KEY = "9C83D4D97C1FB9E8034AE46A1235FA34";
		}
	}

	public static void isAuthorize(boolean flag) {
		// 高级权限
		if (flag) {
			APP_ID = "300008880753";// 现网 欢度春节2 有高级授权
			APP_KEY = "9BB253542D429931AFA8F4D9C013808B";
		} else {
			APP_ID = "300009228352"; // 猪猪侠百变飞车
			APP_KEY = "440591DD9C69F864A2645F7217CC4590";
		}
	}
}
