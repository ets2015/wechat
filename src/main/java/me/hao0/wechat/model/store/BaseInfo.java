package me.hao0.wechat.model.store;

import lombok.Data;

@Data
public class BaseInfo {
    // 门店基础信息字段（重要）
    private String poi_id;
    private String sid;
    private String business_name;
    private String branch_name;
    private String province;
    private String city;
    private String district;
    private String address;
    private String telephone;
    private String categories[];
    private String offset_type;
    private String longitude;
    private String latitude;
    // 门店服务信息字段
    private Photo photo_list[];
    private String recommend;
    private String special;
    private String introduction;
    private String open_time;
    private String avg_price;
    /** 门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空 */
    private Integer available_state;
    /** 扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段 没有在更新中或更新已生效 ，可以再次更新 */
    private Integer update_status;

    @Data
    public static class Photo {
        private String photo_url;
    }
}
