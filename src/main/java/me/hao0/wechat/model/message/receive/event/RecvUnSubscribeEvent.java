package me.hao0.wechat.model.message.receive.event;

/**
 * 取消关注
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 9/11/15
 */
public class RecvUnSubscribeEvent extends RecvEvent {

	private static final long serialVersionUID = 6272803650682495855L;

	public RecvUnSubscribeEvent(RecvEvent e) {
        super(e);
        this.eventType = e.eventType;
    }

    @Override
    public String getEventType() {
        return RecvEventType.UN_SUBSCRIBE.value();
    }

}
