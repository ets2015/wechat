package me.hao0.wechat.model.js;

import java.util.Objects;

/**
 *  卡券签名类型
 * @author Shinez
 * @date 2018/1/18.
 */
public enum CardSignType {

    /**
     *  选择
     */
    CHOOSE("choose"),

    /**
     * 添加
     */
    ADD("add")

    ;


    /**
     * 类型
     */
    private final String type;

    CardSignType(String type) {
        this.type = type;
    }


    public String type() {
        return type;
    }

    public static CardSignType from(String type) {
        for (CardSignType t : CardSignType.values()) {
            if (Objects.equals(t.type(), type)) {
                return t;
            }
        }
        throw new  RuntimeException("CardSignType "+ type + " not found");
    }
}
