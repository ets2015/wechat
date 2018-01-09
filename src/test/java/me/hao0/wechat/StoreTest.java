package me.hao0.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

import me.hao0.wechat.core.Store;
import me.hao0.wechat.core.Wechat;
import me.hao0.wechat.core.WechatBuilder;
import me.hao0.wechat.loader.AccessTokenLoader;
import me.hao0.wechat.model.base.AccessToken;
import me.hao0.wechat.model.store.Business.Content;
import me.hao0.wechat.model.store.GetpoilistResult;

public class StoreTest {
	private Wechat wechat;

	private String accessToken = "5_eNn8p9_KvShrLn9PkHDRELkaZsxBBjzaNAGQOL4sE9AlrJVWOnnDJSxQt1et9EafQOK0ROpRah-6RQ_B8EsTQJ1TFmlBenAXnQpXEypjRW4LfJ3wyr4P8MPBl0dgIDaZo6Hb_OLzUgo-poXyLGRbALDGBM";

//	@Before
	public void init() throws IOException {
		Properties props = new Properties();
		InputStream in = Object.class.getResourceAsStream("/dev.properties");
		props.load(in);

		wechat = WechatBuilder.newBuilder(props.getProperty("appId")).accessTokenLoader(new AccessTokenLoader() {
			
			@Override
			public void refresh(AccessToken token) {
				
			}
			
			@Override
			public String get() {
				return accessToken;
			}
		}).build();
	}
	
//	@Test
	public void getPoiList() {
		Store store = wechat.store();
		GetpoilistResult gr = store.getPoiList(accessToken, "{\"begin\":0, \"limit\":50}");
		System.out.println("getPoiList:"+JSON.toJSON(gr));
	}
	
//	@Test
	public void getPoi() {
		Store store = wechat.store();
		Content b = store.getPoi(accessToken, "{\"poi_id\":484753900}");
		System.out.println(JSON.toJSON(b));
	}
	
//	@Test
	public void updatePoi() {
//		Store store = wechat.store();
//		store.updatePoi(accessToken, "{\"business\":{\"base_info\":{\"poi_id\":\"484753900\",\"business_name\":\"萨达说2\",\"open_time\":\"9:00-20:00\",\"avg_price\":\"10\",\"recommend\":\"\",\"telephone\":\"13605691376\",\"sid\":\"33788392\",\"photo_list\":[{\"photo_url\":\"http://mmbiz.qpic.cn/mmbiz_png/77hoH1TFJIqu21SvmYfRKyHNCNI7fjW5yJMkSv2ibZCJsMQlia8uMG9KGMd6AU98jw0BYibkRWfpQJxHfPzRCeMCQ/0\"}],\"special\":\"\",\"province\":\"广东省\",\"offset_type\":\"1\",\"branch_name\":\" 对方是否\",\"district\":\"市区\",\"categories\":[\"美食,粤菜\",\"美食,粤菜\"],\"introduction\":\"\",\"longitude\":\"114.702461243\"}}}");
	}
	
//	@Test
	public void delPoi() {
		Store store = wechat.store();
		store.delPoi(accessToken, "{\"poi_id\":484753966}");
	}
}
