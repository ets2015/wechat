package me.hao0.wechat.model.js;

import java.io.Serializable;

/**
 * 调用JSSDK前需要加载的配置对象(http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html)
 * Author: guoq
 * Email: guo_daqian@qq.com
 * Date: 18/1/18
 */
public class WxCardConfig extends Config {

    private static final long serialVersionUID = -8263857663686622616L;
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

    public WxCardConfig(String appId, Long timestamp, String nonStr, String signature, String code, Long fixedBegintimestamp, String openid, String outerStr) {
        super(appId, timestamp, nonStr, signature);
        this.code = code;
        this.openid = openid;
        this.fixedBegintimestamp = fixedBegintimestamp;
        this.outerStr = outerStr;
    }

    public WxCardConfig() {
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

    @Override
    public String toString() {
        return "Config{" +
                "appId='" + super.getAppId() + '\'' +
                ", timestamp=" + super.getTimestamp() +
                ", nonStr='" + super.getNonStr() + '\'' +
                ", code='" + code + '\'' +
                ", openid='" + openid + '\'' +
                ", fixedBegintimestamp='" + fixedBegintimestamp + '\'' +
                ", outerStr='" + outerStr + '\'' +
                ", signature='" + super.getSignature() + '\'' +
                '}';
    }
}
