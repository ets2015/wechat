package me.hao0.wechat.model.message.receive.event;

/**
 *  微信连wifi事件
 *  @author Shinez.
 */
public class RecvWifiConnectedEvent extends RecvEvent {
    private static final long serialVersionUID = 3505512643353966367L;

    public RecvWifiConnectedEvent(RecvEvent e) {
        super(e);
        this.eventType = e.getEventType();
    }

    /**
     * 连网时间（整型）
     */
    private Integer connectTime;

    /**
     * 系统保留字段，固定值
     */
    private Integer expireTime;

    /**
     * 系统保留字段，固定值
     */
    private String vendorId;

    /**
     *  门店ID，即shop_id
     */
    private String shopId;

    /**
     *  连网的设备无线mac地址，对应bssid
     */
    private String deviceNo;

    public Integer getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Integer connectTime) {
        this.connectTime = connectTime;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }


    @Override
    public String getEventType() {
        return RecvEventType.WIFI_CONNECTED.value();
    }
}
