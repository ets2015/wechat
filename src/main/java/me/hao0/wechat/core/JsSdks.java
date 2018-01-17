package me.hao0.wechat.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import me.hao0.wechat.model.js.Config;
import me.hao0.wechat.model.js.Ticket;
import me.hao0.wechat.model.js.TicketType;

import java.util.*;

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
     * @param accessToken accessToken
     * @param nonce_str   随机字符串
     * @param timestamp   时间戳(s)
     * @param card_id     卡券id
     * @return
     */
    public Config getWxCardConfig(String accessToken, String nonce_str, Long timestamp, String card_id, String openid) {
        Ticket t = getTicket(accessToken, TicketType.CARD);
        return getWxCardConfig(t.getTicket(), nonce_str, timestamp, card_id, openid, null);
    }

    /**
     * 获取JSSDK-wxcard卡券调用前的配置信息
     *
     * @param nonce_str 随机字符串
     * @param timestamp 时间戳(s)
     * @param card_id   卡券id
     * @param openid    用户openid，不填则为""
     * @param code      卡券code，不填则为""
     * @return
     */
    public Config getWxCardConfig(String nonce_str, Long timestamp, String card_id, String openid, String code) {
        return getWxCardConfig(wechat.loadTicket(TicketType.CARD), nonce_str, timestamp, card_id, openid, code);
    }

    /**
     * 获取JSSDK-wxcard卡券调用前的配置信息
     *
     * @param api_ticket jsapi凭证
     * @param nonce_str  随机字符串
     * @param timestamp  时间戳(s)
     * @param card_id    卡券id
     * @param openid     用户openid，不填则为""
     * @param code       卡券code，不填则为""
     * @return
     */
    public Config getWxCardConfig(String api_ticket, String nonce_str, Long timestamp, String card_id, String openid, String code) {
        checkNotNull(api_ticket, "api_ticket can't be null");
        checkNotNullAndEmpty(nonce_str, "nonStr");
        checkNotNull(timestamp, "timestamp can't be null");
        checkNotNull(card_id, "card_id can't be null");
        List<String> signStrs = new ArrayList<>();
        signStrs.add(api_ticket);
        signStrs.add(nonce_str);
        signStrs.add(timestamp.toString());
        signStrs.add(card_id);
        signStrs.add(openid != null ? openid : "");
        signStrs.add(code != null ? code : "");
        Collections.sort(signStrs);
        String signStr = signStrs.get(0) + signStrs.get(1) + signStrs.get(2) + signStrs.get(3) + signStrs.get(4) + signStrs.get(6);
        String sign = Hashing.sha1().hashString(signStr, Charsets.UTF_8).toString().toLowerCase();
        return new Config(wechat.getAppId(), timestamp, nonce_str, sign);
    }
}
