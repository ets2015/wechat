package me.hao0.wechat.model.message.receive.event.menu;

/**
 * 扫码推事件且弹出“消息接收中”提示框 事件推送
 * @author Shinez.
 */
public class RecvScancodeWaitmsgEvent extends RecvScancodePushEvent {


    private static final long serialVersionUID = -8881891637226006093L;

    public RecvScancodeWaitmsgEvent(RecvScancodePushEvent e) {
        super(e);
        this.eventType = e.getEventType();
    }

    @Override
    public String getEventType() {
        return RecvMenuEventType.SCANCODE_WAITMSG.value();
    }

}
