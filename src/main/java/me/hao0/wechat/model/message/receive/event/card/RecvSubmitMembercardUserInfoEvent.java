package me.hao0.wechat.model.message.receive.event.card;

import me.hao0.wechat.model.message.receive.event.RecvEvent;

/**
 * 会员卡激活事件推送
 * 当用户通过一键激活的方式提交信息并点击激活或者用户修改会员卡信息后，商户会收到用户激活的事件推送
 * @author Shinez.
 */
public class RecvSubmitMembercardUserInfoEvent extends RecvEvent {


    private static final long serialVersionUID = -3572621165215252880L;

    public RecvSubmitMembercardUserInfoEvent(RecvEvent e) {
        super(e);
        this.eventType = e.getEventType();
    }

    /**
     * 卡券ID
     */
    private String cardId;

    /**
     * 卡券Code码
     */
    private String userCardCode;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }

    @Override
    public String getEventType() {
        return RecvCardEventType.SUBMIT_MEMBERCARD_USER_INFO.value();
    }


}
