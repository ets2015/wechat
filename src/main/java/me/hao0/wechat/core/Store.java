package me.hao0.wechat.core;

import static me.hao0.common.util.Preconditions.checkNotNullAndEmpty;

import java.util.Map;

/**
 * 微信门店接口
 * @author zJun
 * @date 2017年12月29日 上午11:05:13
 */
public class Store extends Component {
	
	/** 创建门店 */
	private static final String ADDPOI = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=";
	
	/**
	 * 创建门店
	 * @param accessToken
	 * @param json
	 * @return 门店poi_id
	 * @author zJun
	 * @date 2017年12月29日 上午11:16:00
	 */
	public String createPoi(String accessToken, String json) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(json, "json");

        String url = ADDPOI + accessToken;
        Map<String, Object> resp = doPost(url, json);
        return (String)resp.get("poi_id");
    }
	
	/**
	 * 创建门店
	 * @param json
	 * @return 门店poi_id
	 * @author zJun
	 * @date 2017年12月29日 上午11:16:45
	 */
	public String createPoi(String json) {
		return createPoi(loadAccessToken(), json);
	}
}
