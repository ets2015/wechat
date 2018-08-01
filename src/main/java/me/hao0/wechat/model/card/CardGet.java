package me.hao0.wechat.model.card; 

/**
 * 拉取会员卡信息
 * @author zJun
 * @date 2018年8月1日 下午8:01:27
 */
public class CardGet{
	private String card_id;
	private String code;
	public void setCard_id(String card_id){
		this.card_id = card_id;
	}
	public String getCard_id(){
		return this.card_id;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
}

	/**

	{"card_id":"pbLatjtZ7v1BG_ZnTjbW85GYc_E8","code":"916679873278"}

	*/