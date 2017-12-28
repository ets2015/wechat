package me.hao0.wechat.model.poi;

import lombok.Data;

@Data
public class BaseInfo {
	// 门店基础信息字段（重要）
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
	private Photo photo_list;
	private String recommend;
	private String special;
	private String introduction;
	private String open_time;
	private String avg_price;
	
	@Data
	public static class Photo {
		private String photo_url;
	}
}
