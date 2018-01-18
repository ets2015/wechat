package me.hao0.wechat.model.js;

import java.util.Objects;

/**
 * 临时凭证类型
 */
public enum TicketType {

    /**
     * 用于调用微信JSSDK的临时票据
     */
    JSAPI("jsapi"),

    /**
     * 用于调用卡券相关接口的临时票据
     */
    CARD("wx_card");

    private String type;

    private TicketType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static TicketType from(String type) {
        for (TicketType t : TicketType.values()) {
            if (Objects.equals(t.type(), type)) {
                return t;
            }
        }
        throw new  RuntimeException("Ticket type "+ type + " not found");
    }
}