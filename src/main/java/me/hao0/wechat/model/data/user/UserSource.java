package me.hao0.wechat.model.data.user;

import com.google.common.base.Objects;

/**
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 20/11/15
 */
public enum UserSource {

    /**
     * 其他: 包括带参数二维码
     */
    OTHER(0),

    /**
     * 公众号搜索
     */
    SEARCH(1),

    /**
     * 扫二维码
     */
    @Deprecated
    SCAN(3),

    /**
     * 名片分享
     */
    CARD(17),

    /**
     * 扫描二维码
     */
    SCAN_QR(30),

    /**
     * 代表搜号码（即微信添加朋友页的搜索）
     */
    @Deprecated
    SEARCH_NUMBER(35),

    /**
     * 查询微信公众帐号
     */
    @Deprecated
    SEARCH_ACCOUNT(39),

    /**
     * 图文页右上角菜单
     */
    MENU(43),

    /**
     * 支付后关注
     */
    AFTER_PAY(51),

    /**
     * 图文页内公众号名称
     */
    FROM_MEDIA(57),

    /**
     * 公众号文章广告
     */
    FROM_ARTICLE_AD(75),

    /**
     * 朋友圈广告
     */
    FROM_COMMENT_AD(78)
    ;

    private Integer value;

    UserSource(Integer scope) {
        this.value = scope;
    }

    public static UserSource from(Integer s) {
        for (UserSource source : UserSource.values()) {
            if (Objects.equal(source.value(), s)) {
                return source;
            }
        }
        return null;
    }

    public Integer value() {
        return value;
    }
}
