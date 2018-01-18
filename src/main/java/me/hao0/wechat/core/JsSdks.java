package me.hao0.wechat.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import me.hao0.wechat.model.js.CardConfig;
import me.hao0.wechat.model.js.Config;
import me.hao0.wechat.model.js.Ticket;
import me.hao0.wechat.model.js.TicketType;

import java.util.Arrays;
import java.util.Map;

import static me.hao0.common.util.Preconditions.checkNotNull;
import static me.hao0.common.util.Preconditions.checkNotNullAndEmpty;

/**
 * JS-SDK组件
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 18/11/15
 *
 * @since 1.4.0
 */
public final class JsSdks extends Component {

    /**
     * 获取Ticket
     */
    private static final String TICKET_GET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";

    JsSdks() {
    }

    /**
     * 获取临时凭证
     *
     * @param type 凭证类型
     * @param cb   回调
     * @see me.hao0.wechat.model.js.TicketType
     */
    public void getTicket(final TicketType type, final Callback<Ticket> cb) {
        getTicket(loadAccessToken(), type, cb);
    }

    /**
     * 获取临时凭证
     *
     * @param type 凭证类型
     * @return Ticket对象，或抛WechatException
     * @see me.hao0.wechat.model.js.TicketType
     */
    public Ticket getTicket(TicketType type) {
        return getTicket(loadAccessToken(), type);
    }

    /**
     * 获取临时凭证
     *
     * @param accessToken accessToken
     * @param type        凭证类型
     * @param cb          回调
     * @see me.hao0.wechat.model.js.TicketType
     */
    public void getTicket(final String accessToken, final TicketType type, final Callback<Ticket> cb) {
        doAsync(new AsyncFunction<Ticket>(cb) {
            @Override
            public Ticket execute() {
                return getTicket(accessToken, type);
            }
        });
    }

    /**
     * 获取临时凭证
     *
     * @param accessToken accessToken
     * @param type        凭证类型
     * @return Ticket对象，或抛WechatException
     * @see me.hao0.wechat.model.js.TicketType
     */
    public Ticket getTicket(String accessToken, TicketType type) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNull(type, "ticket type can't be null");

        String url = TICKET_GET + accessToken + "&type=" + type.type();
        Map<String, Object> resp = doGet(url);
        Ticket t = new Ticket();
        t.setTicket((String) resp.get("ticket"));
        Integer expire = (Integer) resp.get("expires_in");
        t.setExpire(expire);
        t.setExpireAt(System.currentTimeMillis() + expire * 1000);
        t.setType(type);

        return t;
    }

    /**
     * 获取JSSDK配置信息
     *
     * @param nonStr 随机字符串
     * @param url    调用JSSDK的页面URL全路径(去除#后的)
     * @return Config对象
     */
    public Config getConfig(String nonStr, String url) {
        return getConfig(wechat.loadTicket(TicketType.JSAPI), nonStr, url);
    }

    /**
     * 获取JSSDK配置信息
     *
     * @param jsApiTicket jsapi凭证
     * @param nonStr      随机字符串
     * @param url         调用JSSDK的页面URL全路径(去除#后的)
     * @return Config对象
     */
    public Config getConfig(String jsApiTicket, String nonStr, String url) {
        return getConfig(jsApiTicket, nonStr, System.currentTimeMillis() / 1000, url);
    }

    /**
     * 获取JSSDK配置信息
     *
     * @param nonStr    随机字符串
     * @param timestamp 时间戳(s)
     * @param url       调用JSSDK的页面URL全路径(去除#后的)
     * @return Config对象
     */
    public Config getConfig(String nonStr, Long timestamp, String url) {
        return getConfig(wechat.loadTicket(TicketType.JSAPI), nonStr, timestamp, url);
    }

    /**
     * 获取JSSDK调用前的配置信息
     *
     * @param jsApiTicket jsapi凭证
     * @param nonStr      随机字符串
     * @param timestamp   时间戳(s)
     * @param url         调用JSSDK的页面URL全路径(去除#后的)
     * @return Config对象
     */
    public Config getConfig(String jsApiTicket, String nonStr, Long timestamp, String url) {
        checkNotNullAndEmpty(jsApiTicket, "jsApiTicket");
        checkNotNullAndEmpty(nonStr, "nonStr");
        checkNotNull(timestamp, "timestamp can't be null");

        String signStr = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        signStr = String.format(signStr, jsApiTicket, nonStr, timestamp, url);
        String sign = Hashing.sha1().hashString(signStr, Charsets.UTF_8).toString().toLowerCase();
        return new Config(wechat.getAppId(), timestamp, nonStr, sign);
    }

    /**
     * 获取JSSDK-wxcard卡券调用前的配置信息
     *
     * @param apiTicket jsapi凭证
     * @param nonceStr  随机字符串
     * @param cardId    卡券id
     * @param outerStr   领取渠道参数
     * @return
     * @auth guoq
     */
    public CardConfig getCardConfig(String apiTicket, String nonceStr, String cardId, String outerStr) {
        return getCardConfig(apiTicket, nonceStr, cardId, null, null, outerStr);
    }

    /**
     * 获取JSSDK-wxcard卡券调用前的配置信息
     *
     * @param apiTicket jsapi凭证
     * @param nonceStr  随机字符串
     * @param cardId    卡券id
     * @param openid     用户openid
     * @param code       卡券code
     * @param outerStr   领取渠道参数
     * @return
     * @auth guoq
     */
    public CardConfig getCardConfig(String apiTicket, String nonceStr, String cardId, String openid, String code, String outerStr) {
        return getCardConfig(apiTicket, nonceStr, System.currentTimeMillis() / 1000, cardId, openid, code, outerStr, null);
    }

    /**
     * 获取JSSDK-wxcard卡券调用前的配置信息
     *
     * @param apiTicket          jsapi凭证
     * @param nonceStr           随机字符串
     * @param timestamp           时间戳(s)
     * @param cardId             卡券id
     * @param openid              用户openid
     * @param code                卡券code
     * @param outerStr            领取渠道参数
     * @param fixedBegintimestamp 卡券在第三方系统的实际领取时间戳（秒）
     * @return
     * @auth guoq
     */
    public CardConfig getCardConfig(String apiTicket, String nonceStr, Long timestamp, String cardId, String openid, String code, String outerStr, Long fixedBegintimestamp) {
        checkNotNull(apiTicket, "apiTicket can't be null");
        checkNotNullAndEmpty(nonceStr, "nonStr");
        checkNotNull(timestamp, "timestamp can't be null");
        checkNotNull(cardId, "cardId can't be null");

        //将value进行字典排序
        int signValLen = 6;
        String[] signStrs = new String[signValLen];
        signStrs[0] = apiTicket;
        signStrs[1] = nonceStr;
        signStrs[2] = timestamp.toString();
        signStrs[3] = cardId;
        signStrs[4] = openid != null ? openid : "";
        signStrs[5] = code != null ? code : "";
        Arrays.sort(signStrs);
        StringBuilder signStr = new StringBuilder();
        for (int i = 0; i < signValLen; i++) {
            signStr.append(signStrs[i]);
        }
        String sign = Hashing.sha1().hashString(signStr.toString(), Charsets.UTF_8).toString().toLowerCase();
        return new CardConfig(wechat.getAppId(), timestamp, nonceStr, sign, code, fixedBegintimestamp, openid, outerStr);
    }
}
