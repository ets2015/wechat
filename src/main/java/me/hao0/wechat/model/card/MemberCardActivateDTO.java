package me.hao0.wechat.model.card;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 会员卡接口激活传输实体
 *
 * @author Shinez
 * @date 2018/1/20.
 */
public class MemberCardActivateDTO implements Serializable {

    private static final long serialVersionUID = -2270766705698539974L;

    /**
     * 会员卡编号，由开发者填入，作为序列号显示在用户的卡包里。可与Code码保持等值。
     */
    private String membershipNumber;

    /**
     * 领取会员卡用户获得的code
     */
    private String code;

    /**
     * 卡券ID,自定义code卡券必填
     */
    private String cardId;

    /**
     * 商家自定义会员卡背景图，须 先调用 上传图片接口 将背景图上传至CDN，否则报错， 卡面设计请遵循 微信会员卡自定义背景设计规范
     */
    private String backgroundPicUrl;

    /**
     * 激活后的有效起始时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
     */
    private Long activateBeginTime;

    /**
     * 激活后的有效截至时间。若不填写默认以创建时的 data_info 为准。Unix时间戳格式。
     */
    private Long activateEndTime;


    /**
     * 初始积分
     */
    private Integer initBonus;

    /**
     * 积分同步说明。
     */
    private String initBonusRecord;

    /**
     * 初始余额，不填为0。
     */
    private Integer initBalance;

    /**
     * 创建时字段custom_field1定义类型的初始值，限制为4个汉字，12字节。
     */
    private String initCustomFieldValue1;

    /**
     * 创建时字段custom_field2定义类型的初始值，限制为4个汉字，12字节。
     */
    private String initCustomFieldValue2;

    /**
     * 创建时字段custom_field3定义类型的初始值，限制为4个汉字，12字节。
     */
    private String initCustomFieldValue3;


    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public Long getActivateBeginTime() {
        return activateBeginTime;
    }

    public void setActivateBeginTime(Long activateBeginTime) {
        this.activateBeginTime = activateBeginTime;
    }

    public Long getActivateEndTime() {
        return activateEndTime;
    }

    public void setActivateEndTime(Long activateEndTime) {
        this.activateEndTime = activateEndTime;
    }

    public Integer getInitBonus() {
        if (initBonus == null) {
            initBonus = 0;
        }
        return initBonus;
    }

    public void setInitBonus(Integer initBonus) {
        this.initBonus = initBonus;
    }

    public String getInitBonusRecord() {
        return initBonusRecord;
    }

    public void setInitBonusRecord(String initBonusRecord) {
        this.initBonusRecord = initBonusRecord;
    }

    public Integer getInitBalance() {
        if (initBalance == null) {
            initBalance = 0;
        }
        return initBalance;
    }

    public void setInitBalance(Integer initBalance) {
        this.initBalance = initBalance;
    }

    public String getInitCustomFieldValue1() {
        return initCustomFieldValue1;
    }

    public void setInitCustomFieldValue1(String initCustomFieldValue1) {
        this.initCustomFieldValue1 = initCustomFieldValue1;
    }

    public String getInitCustomFieldValue2() {
        return initCustomFieldValue2;
    }

    public void setInitCustomFieldValue2(String initCustomFieldValue2) {
        this.initCustomFieldValue2 = initCustomFieldValue2;
    }

    public String getInitCustomFieldValue3() {
        return initCustomFieldValue3;
    }

    public void setInitCustomFieldValue3(String initCustomFieldValue3) {
        this.initCustomFieldValue3 = initCustomFieldValue3;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
