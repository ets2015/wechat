package me.hao0.wechat.model.message.receive.event.menu;


import java.util.Objects;

/**
 * 自定义菜单事件推送类型
 * @author Shinez.
 */
public enum RecvMenuEventType {


    UNKNOW("unknown", "未知自定义菜单事件"),


    /**
     * 点击菜单拉取消息时的事件推送
     */
    CLICK("CLICK", "点击菜单拉取消息时的事件推送"),

    /**
     * 点击菜单跳转链接时的事件推送
     */
    VIEW("VIEW", "点击菜单跳转链接时的事件推送"),

    /**
     * 扫码推事件的事件推送
     */
    SCANCODE_PUSH("scancode_push", " 扫码推事件的事件推送"),

    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件推送
     */
    SCANCODE_WAITMSG("scancode_waitmsg", "扫码推事件且弹出“消息接收中”提示框的事件推送"),

    /**
     * 弹出系统拍照发图的事件推送
     */
    PIC_SYSPHOTO("pic_sysphoto", "弹出系统拍照发图的事件推送"),

    /**
     * 弹出拍照或者相册发图的事件推送
     */
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "弹出拍照或者相册发图的事件推送"),

    /**
     * 弹出微信相册发图器的事件推送
     */
    PIC_WEIXIN("pic_weixin", "弹出微信相册发图器的事件推送"),

    /**
     * 弹出地理位置选择器的事件推送
     */
    LOCATION_SELECT("location_select", "弹出地理位置选择器的事件推送");


    private String value;

    private String desc;


    RecvMenuEventType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String value() {
        return value;
    }

    public static RecvMenuEventType from(String type) {
        for (RecvMenuEventType t : RecvMenuEventType.values()) {
            if (Objects.equals(t.value(), type)) {
                return t;
            }
        }

        return UNKNOW;
    }

    @Override
    public String toString() {
        return "RecvMenuEventType{" +
                "value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
