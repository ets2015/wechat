package me.hao0.wechat.model.js;


/**
 * 调用JSSDK前需要加载的配置对象(http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html)
 *
 * @author guoq
 * @Email: guo_daqian@qq.com
 * @Date: 18/1/18
 */
public class CardConfig extends Config {

    private static final long serialVersionUID = -8266857663696622616L;


    /**
     * 卡券ID
     */
    private String cardId;


    /**
     * 卡券code
     */
    private String code;

    /**
     * 卡券在第三方系统的实际领取时间，为东八区时间戳（UTC+8,精确到秒）
     * 当卡券的有效期类型为 DAT E_TYPE_FIX_TERM时专用，标识卡券的实际生效时间，用于解决商户系统内起始时间和领取时间不同步的问题。
     */
    private Long fixedBegintimestamp;

    /**
     * 指定领取者的openid
     */
    private String openid;

    /**
     * 领取渠道参数
     */
    private String outerStr;

    /**
     * 门店ID
     */
    private String locationId;

    /**
     * 优惠券类型
     */
    private String cardType;

    /**
     * 红包类型卡券专属
     */
    private String balance;

    public CardConfig() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFixedBegintimestamp() {
        return fixedBegintimestamp;
    }

    public void setFixedBegintimestamp(Long fixedBegintimestamp) {
        this.fixedBegintimestamp = fixedBegintimestamp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOuterStr() {
        return outerStr;
    }

    public void setOuterStr(String outerStr) {
        this.outerStr = outerStr;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBalance() {
        return balance;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
