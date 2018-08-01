package me.hao0.wechat.model.card; 


import java.util.List;

import me.hao0.wechat.model.base.WechatResponse;

/**
 * 拉取会员卡信息 返回结果
 * @author zJun
 * @date 2018年8月1日 下午8:07:48
 */
public class CardGetResult extends WechatResponse {
    
	private String openid;
	private String nickname;
	private String membership_number;
	private int bonus;
	private String sex;
	private User_infoBean user_info;
	private String user_card_status;
	private boolean has_active;
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public String getOpenid(){
		return this.openid;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getNickname(){
		return this.nickname;
	}
	public void setMembership_number(String membership_number){
		this.membership_number = membership_number;
	}
	public String getMembership_number(){
		return this.membership_number;
	}
	public void setBonus(int bonus){
		this.bonus = bonus;
	}
	public int getBonus(){
		return this.bonus;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getSex(){
		return this.sex;
	}
	public User_infoBean getUser_info(){
		return this.user_info;
	}
	public void setUser_card_status(String user_card_status){
		this.user_card_status = user_card_status;
	}
	public String getUser_card_status(){
		return this.user_card_status;
	}
	public void setHas_active(boolean has_active){
		this.has_active = has_active;
	}
	public boolean getHas_active(){
		return this.has_active;
	}
	public static class User_infoBean{
		private List<Common_field_listBean> common_field_list;
		private List<Custom_field_listBean> custom_field_list;
		public void setCommon_field_list(List<Common_field_listBean> common_field_list){
			this.common_field_list = common_field_list;
		}
		public List<Common_field_listBean>  getCommon_field_list(){
			return this.common_field_list;
		}
		public void setCustom_field_list(List<Custom_field_listBean> custom_field_list){
			this.custom_field_list = custom_field_list;
		}
		public List<Custom_field_listBean>  getCustom_field_list(){
			return this.custom_field_list;
		}
		public static class Common_field_listBean{
			private String name;
			private String value;
			public void setName(String name){
				this.name = name;
			}
			public String getName(){
				return this.name;
			}
			public void setValue(String value){
				this.value = value;
			}
			public String getValue(){
				return this.value;
			}
		}
		public static class Custom_field_listBean{
			private String name;
			private String value;
			private List<Value_listBean> value_list;
			public void setName(String name){
				this.name = name;
			}
			public String getName(){
				return this.name;
			}
			public void setValue(String value){
				this.value = value;
			}
			public String getValue(){
				return this.value;
			}
			public void setValue_list(List<Value_listBean> value_list){
				this.value_list = value_list;
			}
			public List<Value_listBean>  getValue_list(){
				return this.value_list;
			}
			public static class Value_listBean{
			}
		}
	}
}

	/**

	{"errcode":0,"errmsg":"ok","openid":"obLatjjwDolFj******wNqRXw","nickname":"*******","membership_number":"658*****445","bonus":995,"sex":"MALE","user_info":{"common_field_list":[{"name":"USER_FORM_INFO_FLAG_MOBILE","value":"15*****518"},{"name":"USER_FORM_INFO_FLAG_NAME","value":"HK"},{"name":"USER_FORM_INFO_FLAG_EDUCATION_BACKGROUND","value":"研究生"}],"custom_field_list":[{"name":"兴趣","value":"钢琴","value_list":[]},{"name":"喜好","value":"郭敬明","value_list":[]},{"name":"职业","value":"","value_list":["赛车手","旅行家"]}]},"user_card_status":"NORMAL","has_active":false}

	*/