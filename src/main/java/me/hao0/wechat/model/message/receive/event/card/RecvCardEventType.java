package me.hao0.wechat.model.message.receive.event.card;


import java.util.Objects;

/**
 *  卡券事件类型
 * @author Shinez.
 */
public enum RecvCardEventType {


    UNKNOW("unknown", "未知卡券事件"),

    /**
     *  生成的卡券通过审核时
     */
    CARD_PASS_CHECK("card_pass_check", "卡券通过审核"),

    /**
     * 卡券未通过审核事件
     */
    CARD_NOT_PASS_CHECK("card_not_pass_check","卡券未通过审核"),

    /**
     *  用户在领取卡券时
     */
    USER_GET_CARD("user_get_card", " 领取事件推送"),

    /**
     *  用户在转赠卡券时
     */
    USER_GIFTING_CARD("user_gifting_card","转赠事件推送"),

    /**
     *  用户在删除卡券时
     */
    USER_DEL_CARD("user_del_card","删除事件推送"),

    /**
     *  卡券被核销时
     */
    USER_CONSUME_CARD("user_consume_card","核销事件推送"),

    /**
     *  微信买单完成时
     */
    USER_PAY_FROM_PAY_CELL("user_pay_from_pay_cell","买单事件推送"),

    /**
     * 用户在进入会员卡时，微信会把这个事件推送到开发者填写的URL。
       需要开发者在创建会员卡时填入need_push_on_view	字段并设置为true。
       开发者须综合考虑领卡人数和服务器压力，决定是否接收该事件
     */
    USER_VIEW_CARD("user_view_card","进入会员卡事件推送"),
    /**
     *  用户在卡券里点击查看公众号进入会话时（需要用户已经关注公众号），微信会把这个事件推送到开发者填写的URL。
     *  开发者可识别从卡券进入公众号的用户身份。
     */
    USER_ENTER_SESSION_FROM_CARD("user_enter_session_from_card","从卡券进入公众号会话事件推送"),

    /**
     * 当用户的会员卡积分余额发生变动时，微信会推送事件告知开发者。
     */
    UPDATE_MEMBER_CARD("update_member_card","会员卡内容更新事件"),

    /**
     *  当某个card_id的初始库存数大于200且当前库存小于等于100时，
     *   用户尝试领券会触发发送事件给商户，事件每隔12h发送一次。
     */
    CARD_SKU_REMIND("card_sku_remind","库存报警事件"),

    /**
     *  当商户朋友的券券点发生变动时，微信服务器会推送消息给商户服务器。
     */
    CARD_PAY_ORDER("card_pay_order","券点流水详情事件"),

    /**
     *  当用户通过一键激活的方式提交信息并点击激活或者用户修改会员卡信息后，商户会收到用户激活的事件推送
     */
    SUBMIT_MEMBERCARD_USER_INFO("submit_membercard_user_info","会员卡激活事件推送"),

    ;



    private String value;

    private String desc;


    RecvCardEventType(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String value(){
        return value;
    }

    public static RecvCardEventType from(String type){
        for (RecvCardEventType t : RecvCardEventType.values()){
            if (Objects.equals(t.value(), type)){
                return t;
            }
        }

        return UNKNOW;
    }

    @Override
    public String toString() {
        return "RecvEventCard{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
