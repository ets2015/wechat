package me.hao0.wechat.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.hao0.wechat.model.store.Business.Content;
import me.hao0.wechat.model.store.GetpoilistResult;

import java.util.List;
import java.util.Map;

/**
 * 微信门店接口
 *
 * @author zJun
 * @date 2017年12月29日 上午11:05:13
 */
public class Store extends Component {

    /**
     * 创建门店
     */
    private static final String ADDPOI = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=";

    /**
     * 查询门店列表
     */
    private static final String GETPOILIST = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=";

    /**
     * 查询门店信息
     */
    private static final String GETPOI = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=";

    /**
     * 修改门店服务信息
     */
    private static final String UPDATEPOI = "https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=";

    /**
     * 删除门店
     */
    private static final String DELPOI = "https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=";

    /**
     * 创建门店
     *
     * @param accessToken
     * @param json
     * @return 门店poi_id
     * @author zJun
     * @date 2017年12月29日 上午11:16:00
     */
    public String createPoi(String accessToken, String json) {
        checkData(accessToken, json);

        String url = ADDPOI + accessToken;
        Map<String, Object> resp = doPost(url, json);
        return (String) resp.get("poi_id");
    }

    /**
     * 创建门店
     *
     * @param json
     * @return 门店poi_id
     * @author zJun
     * @date 2017年12月29日 上午11:16:45
     */
    public String createPoi(String json) {
        return createPoi(loadAccessToken(), json);
    }

    /**
     * 查询门店列表
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:35:29
     */
    public GetpoilistResult getPoiList(String accessToken, String json) {
        checkData(accessToken, json);

        String url = GETPOILIST + accessToken;
        Map<String, Object> resp = doPost(url, json);
        String text = JSONObject.toJSONString(resp.get("business_list"));
        GetpoilistResult gr = new GetpoilistResult();
        List<Content> list = JSON.parseArray(text, Content.class);
        gr.setList(list);
        gr.setTotal_count((Integer) resp.get("total_count"));
        return gr;
    }

    /**
     * 查询门店列表
     *
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:36:16
     */
    public GetpoilistResult getPoiList(String json) {
        return getPoiList(loadAccessToken(), json);
    }

    /**
     * 查询门店信息
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:52:33
     */
    public Content getPoi(String accessToken, String json) {
        checkData(accessToken, json);

        String url = GETPOI + accessToken;
        Map<String, Object> resp = doPost(url, json);
        String text = JSONObject.toJSONString(resp.get("business"));
        Content c = JSON.parseObject(text, Content.class);
        return c;
    }

    /**
     * 查询门店信息
     *
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:52:57
     */
    public Content getPoi(String json) {
        return getPoi(loadAccessToken(), json);
    }

    /**
     * 修改门店服务信息
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:55:35
     */
    public void updatePoi(String accessToken, String json) {
        checkData(accessToken, json);

        String url = UPDATEPOI + accessToken;
        doPost(url, json);
    }

    /**
     * 修改门店服务信息
     *
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午3:55:35
     */
    public void updatePoi(String json) {
        updatePoi(loadAccessToken(), json);
    }

    /**
     * 删除门店
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午4:46:54
     */
    public void delPoi(String accessToken, String json) {
        checkData(accessToken, json);

        String url = DELPOI + accessToken;
        doPost(url, json);
    }

    /**
     * 删除门店
     *
     * @param json
     * @author zJun
     * @date 2018年1月5日 下午4:46:54
     */
    public void delPoi(String json) {
        delPoi(loadAccessToken(), json);
    }
}

