package me.hao0.wechat.core;

import static me.hao0.common.util.Preconditions.checkNotNullAndEmpty;

import java.util.Map;

import me.hao0.wechat.model.card.CardGet;
import me.hao0.wechat.model.card.CardGetResult;
import me.hao0.wechat.model.card.CardSet;
import me.hao0.wechat.model.card.MemberCardActivateDTO;
import me.hao0.wechat.model.card.TestWhiteListDTO;
import me.hao0.wechat.model.card.UpdateUserDTO;
import me.hao0.wechat.model.card.UpdateUserResponse;

/**
 * 微信卡券
 *
 * @author zJun
 * @date 2017年12月8日 下午3:37:10
 */
public class Cards extends Component {


    /**
     * 创建卡券
     */
    private static final String CREATE = "https://api.weixin.qq.com/card/create?access_token=";

    /**
     * 创建卡券二维码
     */
    private static final String QRCODE = "https://api.weixin.qq.com/card/qrcode/create?access_token=";

    /**
     * 设置买单接口
     */
    private static final String PAYCELL_SET = "https://api.weixin.qq.com/card/paycell/set?access_token=";

    /**
     * 设置是否开启自助核销功能
     */
    private static final String SELF_CONSUME_CELL = "https://api.weixin.qq.com/card/selfconsumecell/set?access_token=";

    /**
     * 更改卡券信息接口
     */
    private static final String UPDATE = "https://api.weixin.qq.com/card/update?access_token=";

    /**
     * 核销Code接口
     */
    private static final String CONSUME = "https://api.weixin.qq.com/card/code/consume?access_token=";

    /**
     * 修改卡券库存接口
     */
    private static final String MODIFYSTOCK = "https://api.weixin.qq.com/card/modifystock?access_token=";

    /**
     * 删除卡券接口
     */
    private static final String DELETE = "https://api.weixin.qq.com/card/delete?access_token=";

    /**
     * 设置卡券失效接口
     */
    private static final String UNAVAILABLE = "https://api.weixin.qq.com/card/code/unavailable?access_token=";

    /**
     * 查询卡券详情
     */
    private static final String GET = "https://api.weixin.qq.com/card/get?access_token=";

    /**
     * 查询卡券列表
     */
    private static final String BATCHGET = "https://api.weixin.qq.com/card/batchget?access_token=";
    
    /**
     * 创建会员卡接口
     */
    private static final String MEMBER_CREATE = "https://api.weixin.qq.com/card/create?access_token=";

    /**
     * 微信card_id
     */
    private static final String CARD_ID = "card_id";


    /**
     * 接口激活会员卡
     */
    private static final String MEMBERCARD_ACTIVATE = "https://api.weixin.qq.com/card/membercard/activate?access_token=";

    /**
     * 设置测试白名单
     */
    private static final String TEST_WHITE_LIST_SET = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=";
    
    /**
     * 更新会员信息
     */
    private static final String UPDATEUSER ="https://api.weixin.qq.com/card/membercard/updateuser?access_token=";
    
    /** 步骤二：设置开卡字段接口 */
    private static final String CARD_SET = "https://api.weixin.qq.com/card/membercard/activateuserform/set?access_token=";
    
    /** 步骤五：拉取会员信息接口 */
    private static final String CARD_GET = "https://api.weixin.qq.com/card/membercard/userinfo/get?access_token=";

    /**
     * 创建卡券
     *
     * @param jsonCard 卡券的JSON
     * @return 返回card_id
     * @author zJun
     * @date 2017年12月12日 下午2:36:09
     */
    public String create(String jsonCard) {
        return create(loadAccessToken(), jsonCard);
    }


    /**
     * 创建卡券
     *
     * @param jsonCard 卡券的JSON
     * @param cb       回调
     * @author zJun
     * @date 2017年12月12日 下午2:37:02
     */
    public void create(final String jsonCard, Callback<String> cb) {
        create(loadAccessToken(), jsonCard, cb);
    }


    /**
     * 创建卡券
     *
     * @param accessToken 访问token
     * @param json        卡券的JSON
     * @return 返回card_id
     * @author zJun
     * @date 2017年12月11日 下午8:32:04
     */
    public String create(String accessToken, String json) {
        checkData(accessToken, json);

        String url = CREATE + accessToken;
        Map<String, Object> result = doPost(url, json);
        return result.get(CARD_ID).toString();
    }

    /**
     * 创建卡券
     *
     * @param accessToken 访问token
     * @param jsonCard    卡券的JSON
     * @param cb          回调
     * @author zJun
     * @date 2017年12月12日 下午2:34:13
     */
    public void create(final String accessToken, final String jsonCard, Callback<String> cb) {
        doAsync(new AsyncFunction<String>(cb) {
            @Override
            public String execute() {
                return create(accessToken, jsonCard);
            }
        });
    }

    /**
     * 创建卡券二维码投放
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月14日 上午10:52:04
     */
    public Map<String, Object> createQrcode(String accessToken, String json) {
        checkData(accessToken, json);

        String url = QRCODE + accessToken;
        return doPost(url, json);
    }

    /**
     * 创建卡券二维码
     *
     * @param json
     * @author zJun
     * @date 2017年12月14日 下午2:26:11
     */
    public Map<String, Object> createQrcode(String json) {
        return createQrcode(loadAccessToken(), json);
    }

    /**
     * 创建卡券二维码
     *
     * @param accessToken
     * @param json
     * @param cb
     * @author zJun
     * @date 2017年12月14日 下午2:32:37
     */
    public void createQrcode(final String accessToken, final String json, Callback<Map<String, Object>> cb) {
        doAsync(new AsyncFunction<Map<String, Object>>(cb) {
            @Override
            public Map<String, Object> execute() {
                return createQrcode(accessToken, json);
            }
        });
    }

    /**
     * 创建卡券二维码
     *
     * @param json
     * @param cb
     * @author zJun
     * @date 2017年12月14日 下午2:33:41
     */
    public void createQrcode(final String json, Callback<Map<String, Object>> cb) {
        doAsync(new AsyncFunction<Map<String, Object>>(cb) {
            @Override
            public Map<String, Object> execute() {
                return createQrcode(loadAccessToken(), json);
            }
        });
    }

    /**
     * 设置卡券是否开启买单功能
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月14日 下午6:54:01
     */
    public void paycellSet(String accessToken, String json) {
        checkData(accessToken, json);

        String url = PAYCELL_SET + accessToken;
        doPost(url, json);
    }

    /**
     * 设置卡券是否开启买单功能
     *
     * @param json
     * @author zJun
     * @date 2017年12月14日 下午6:57:53
     */
    public void paycellSet(String json) {
        paycellSet(loadAccessToken(), json);
    }

    /**
     * 设置是否开启自助核销
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月15日 上午10:56:43
     */
    public void selfConsumeCell(String accessToken, String json) {
        checkData(accessToken, json);

        String url = SELF_CONSUME_CELL + accessToken;
        doPost(url, json);
    }

    /**
     * 设置是否开启自助核销
     *
     * @param json
     * @author zJun
     * @date 2017年12月15日 上午10:57:32
     */
    public void selfConsumeCell(String json) {
        selfConsumeCell(loadAccessToken(), json);
    }


    /**
     * 更改卡券信息接口
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月18日 下午6:11:03
     */
    public Map<String, Object> update(String accessToken, String json) {
        checkData(accessToken, json);

        String url = UPDATE + accessToken;
        Map<String, Object> map = doPost(url, json);
        return map;
    }

    /**
     * 更改卡券信息接口
     *
     * @param json
     * @author zJun
     * @date 2017年12月18日 下午6:11:03
     */
    public Map<String, Object> update(String json) {
       return update(loadAccessToken(), json);
    }

    /**
     * 核销Code接口
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月20日 上午11:41:00
     */
    public Map<String, Object> consume(String accessToken, String json) {
        checkData(accessToken, json);

        String url = CONSUME + accessToken;
        return doPost(url, json);
    }

    /**
     * 核销Code接口
     *
     * @param json
     * @author zJun
     * @date 2017年12月20日 上午11:41:43
     */
    public Map<String, Object> consume(String json) {
        return consume(loadAccessToken(), json);
    }

    /**
     * 修改库存接口
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月20日 下午5:23:33
     */
    public void modifystock(String accessToken, String json) {
        checkData(accessToken, json);

        String url = MODIFYSTOCK + accessToken;
        doPost(url, json);
    }

    /**
     * 修改库存接口
     *
     * @param json
     * @author zJun
     * @date 2017年12月25日 下午7:21:49
     */
    public void modifystock(String json) {
        modifystock(loadAccessToken(), json);
    }

    /**
     * 删除微信卡券
     *
     * @param accessToken
     * @param json
     * @author zJun
     * @date 2017年12月26日 下午3:54:16
     */
    public void delete(String accessToken, String json) {
        checkData(accessToken, json);

        String url = DELETE + accessToken;
        doPost(url, json);
    }

    /**
     * 删除微信卡券
     *
     * @param json
     * @author zJun
     * @date 2017年12月26日 下午3:54:29
     */
    public void delete(String json) {
        delete(loadAccessToken(), json);
    }

    /**
     * 设置卡券失效
     *
     * @param json
     * @author zJun
     * @date 2017年12月26日 下午4:00:32
     */
    public void unavailable(String accessToken, String json) {
        checkData(accessToken, json);

        String url = UNAVAILABLE + accessToken;
        doPost(url, json);
    }

    /**
     * 设置卡券失效
     *
     * @param json
     * @author zJun
     * @date 2017年12月26日 下午4:00:32
     */
    public void unavailable(String json) {
        unavailable(loadAccessToken(), json);
    }

    /**
     * 批量查询卡券列表
     *
     * @author zJun
     * @date 2018年1月8日 下午5:36:33
     */
    public Map<String, Object> batchget(String accessToken, String json) {
        checkData(accessToken, json);

        String url = BATCHGET + accessToken;
        return doPost(url, json);
    }

    /**
     * 批量查询卡券列表
     *
     * @author zJun
     * @date 2018年1月8日 下午5:36:33
     */
    public Map<String, Object> batchget(String json) {
        return batchget(loadAccessToken(), json);
    }

    /**
     * 查询卡券详情
     *
     * @author zJun
     * @date 2018年1月8日 下午5:40:45
     */
    public Map<String, Object> get(String accessToken, String json) {
        checkData(accessToken, json);
        String url = GET + accessToken;
        return doPost(url, json);
    }

    /**
     * 查询卡券详情
     *
     * @author zJun
     * @date 2018年1月8日 下午5:40:45
     */
    public Map<String, Object> get(String json) {
        return get(loadAccessToken(), json);
    }


    /**
     * 接口激活会员卡
     *
     * @param accessToken
     * @param memberCardActivateDTO
     * @return
     * @author Shinez
     * @data 2018年1月20日 21:21:42
     */
    public void memberCardActivate(String accessToken, MemberCardActivateDTO memberCardActivateDTO) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        assert memberCardActivateDTO != null;
        String url = MEMBERCARD_ACTIVATE + accessToken;
        doPost(url, memberCardActivateDTO);
    }


    /**
     * 接口激活会员卡
     *
     * @param memberCardActivateDTO
     * @return
     * @author Shinez
     * @data 2018年1月20日 21:21:42
     */
    public void memberCardActivate(MemberCardActivateDTO memberCardActivateDTO) {
        memberCardActivate(loadAccessToken(), memberCardActivateDTO);
    }
    
    /**
     * 创建会员卡接口
     * @author zJun
     * @date 2018年1月19日 下午5:21:21
     */
    public String memberCreate(String accessToken, String json) {
        checkData(accessToken, json);
        String url = MEMBER_CREATE + accessToken;
        Map<String, Object> result = doPost(url, json);
        return result.get(CARD_ID).toString();
    }
    
    /**
     * 创建会员卡接口
     * @author zJun
     * @date 2018年1月19日 下午5:21:27
     */
    public String memberCreate(String json) {
        return memberCreate(loadAccessToken(), json);
    }
    
    /**
     * 设置测试白名单
     * @author zJun
     * @date 2018年1月22日 下午6:06:58
     */
    public void testWhiteListSet(String accessToken, TestWhiteListDTO param) {
    	checkNotNullAndEmpty(accessToken, "accessToken");
        String url = TEST_WHITE_LIST_SET + accessToken;
        doPost(url, param);
    }
    
    /**
     * 设置测试白名单
     * @author zJun
     * @date 2018年1月22日 下午6:07:36
     */
    public void testWhiteListSet(TestWhiteListDTO param) {
    	testWhiteListSet(loadAccessToken(), param);
    }
    
    /**
     * 更新会员信息
     * @author zJun
     * @date 2018年1月23日 下午3:12:06
     */
    public UpdateUserResponse updateuser(String accessToken, UpdateUserDTO param) {
    	checkNotNullAndEmpty(accessToken, "accessToken");
        String url = UPDATEUSER + accessToken;
        return doPost(url, param, UpdateUserResponse.class);
    }
    
    /**
     * 更新会员信息
     * @author zJun
     * @date 2018年1月23日 下午3:12:06
     */
    public UpdateUserResponse updateuser(UpdateUserDTO param) {
        return updateuser(loadAccessToken(), param);
    }
    
    /**
     * 步骤二：设置开卡字段接口
     * @param accessToken
     * @param cardSet
     * @author zJun
     * @date 2018年8月1日 下午5:20:27
     */
    public void cardSet(String accessToken, CardSet cardSet) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        String url = CARD_SET + accessToken;
        doPost(url, cardSet);
    }
    
    /**
     * 步骤二：设置开卡字段接口
     * @param cardSet
     * @author zJun
     * @date 2018年8月1日 下午5:21:32
     */
    public void cardSet( CardSet cardSet) {
        cardSet(loadAccessToken(), cardSet);
    }
    
    /**
     * 步骤五：拉取会员信息接口
     * @param accessToken
     * @param cardGet
     * @return
     * @author zJun
     * @date 2018年8月1日 下午8:13:59
     */
    public CardGetResult cardGet(String accessToken, CardGet cardGet) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        String url = CARD_GET + accessToken;
        return doPost(url, cardGet, CardGetResult.class);
    }
    
    /**
     * 步骤五：拉取会员信息接口
     * @param accessToken
     * @param cardGet
     * @return
     * @author zJun
     * @date 2018年8月1日 下午8:13:59
     */
    public CardGetResult cardGet(CardGet cardGet) {
        return cardGet(loadAccessToken(), cardGet);
    }
}
