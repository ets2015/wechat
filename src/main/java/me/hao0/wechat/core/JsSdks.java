package me.hao0.wechat.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import me.hao0.wechat.model.js.*;

import java.util.Map;
import java.util.TreeSet;

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
     * 获取JSSDK-卡券签名
     * @param apiTicket
     * @param cardConfig
     * @param cardSignType
     * @return CardConfig
     * @Auth Shinez
     */
    public CardConfig getCardConfig(String apiTicket, CardConfig cardConfig, CardSignType cardSignType){
        if(CardSignType.CHOOSE.equals(cardSignType)){
            return getCardConfigByChooseCard(apiTicket,cardConfig);
        }
        return getCardConfigByAddCard(apiTicket,cardConfig);
    }

    /**
     * 获取JSSDK-addCard 签名
     *
     * @param apiTicket  jsapi凭证
     * @param cardConfig 卡券签名配置
     * @return CardConfig
     * @auth guoq
     */
    public CardConfig getCardConfigByAddCard(String apiTicket, CardConfig cardConfig) {
        checkNotNull(apiTicket, "api_ticket can't be null");
        checkNotNull(cardConfig.getCardId(), "card_id can't be null");
        checkNotNull(cardConfig.getNonStr(), "nonce_str can't be null");
        checkNotNull(cardConfig.getTimestamp(), "timestamp can't be null");

        String code = cardConfig.getCode();
        code = code == null ? "" : code;

        String openid = cardConfig.getOpenid();
        openid = openid == null ? "" : openid;

        String balance = cardConfig.getBalance();
        balance = balance == null ? "" : balance;

        TreeSet<String> treeSet =new TreeSet<>();
        treeSet.add(apiTicket);
        treeSet.add(String.valueOf(cardConfig.getTimestamp()));
        treeSet.add(cardConfig.getNonStr());
        treeSet.add(cardConfig.getCardId());
        treeSet.add(code);
        treeSet.add(openid);
        treeSet.add(balance);

        StringBuilder signStr = new StringBuilder("");
        for (String s : treeSet) {
            signStr.append(s);
        }
        String sign = Hashing.sha1().hashString(signStr.toString(), Charsets.UTF_8).toString().toLowerCase();
        cardConfig.setSignature(sign);
        return cardConfig;
    }
    /**
     * 获取JSSDK-chooseCard 签名
     *
     * @param apiTicket  jsapi凭证
     * @param cardConfig 卡券签名配置
     * @return CardConfig
     * @auth Shinez
     */
    public CardConfig getCardConfigByChooseCard(String apiTicket, CardConfig cardConfig) {
        checkNotNull(apiTicket, "api_ticket can't be null");
        checkNotNull(cardConfig.getCardId(), "card_id can't be null");
        checkNotNull(cardConfig.getNonStr(), "nonce_str can't be null");
        checkNotNull(cardConfig.getTimestamp(), "timestamp can't be null");
        checkNotNull(cardConfig.getAppId(), "appid can't be null");
        checkNotNull(cardConfig.getLocationId(), "location_id can't be null");
        checkNotNull(cardConfig.getCardType(), "card_type can't be null");


        TreeSet<String> treeSet =new TreeSet<>();
        treeSet.add(apiTicket);
        treeSet.add(String.valueOf(cardConfig.getTimestamp()));
        treeSet.add(cardConfig.getNonStr());
        treeSet.add(cardConfig.getCardId());
        treeSet.add(cardConfig.getAppId());
        treeSet.add(cardConfig.getLocationId());
        treeSet.add(cardConfig.getCardType());

        StringBuilder signStr = new StringBuilder("");
        for (String s : treeSet) {
            signStr.append(s);
        }
        String sign = Hashing.sha1().hashString(signStr.toString(), Charsets.UTF_8).toString().toLowerCase();
        cardConfig.setSignature(sign);
        return cardConfig;
    }





}
