package me.hao0.wechat.model.message.receive.event;

/**
 * 关注事件
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 9/11/15
 */
public class RecvSubscribeEvent extends RecvEvent {

	private static final long serialVersionUID = -7325516552201687962L;

	/**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    private RecvSubscribeEvent(){
    }

    public RecvSubscribeEvent(RecvEvent e) {
        super(e);
        this.eventType = e.eventType;
    }

    @Override
    public String getEventType() {
        return RecvEventType.SUBSCRIBE.value();
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "RecvSubcribeEvent{" +
                "eventKey='" + eventKey + '\'' +
                ", ticket='" + ticket + '\'' +
                "} " + super.toString();
    }
}
