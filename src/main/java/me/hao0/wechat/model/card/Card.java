package me.hao0.wechat.model.card;

import lombok.Data;

@Data
public class Card {
	
	private CardInfo card;

	/**
	 * 更新卡券基础信息
	 * @param base_info
	 * @author zJun
	 * @date 2017年12月18日 下午5:57:07
	 */
	public void updateBaseInfo(BaseInfo base_info) {
		if(card.getCard_type().equals("CASH"))
			card.getCash().setBase_info(base_info);
		else if(card.getCard_type().equals("DISCOUNT"))
			card.getDiscount().setBase_info(base_info);
		else if(card.getCard_type().equals("GIFT"))
			card.getGift().setBase_info(base_info);
		else 
			throw new RuntimeException("类型错误");
	}
	
	/**
	 * 获得卡券基础信息
	 * @author zJun
	 * @date 2017年12月18日 下午6:01:32
	 */
	public BaseInfo returnBaseInfo() {
		if(card.getCard_type().equals("CASH"))
			return card.getCash().getBase_info();
		else if(card.getCard_type().equals("DISCOUNT"))
			return card.getDiscount().getBase_info();
		else if(card.getCard_type().equals("GIFT"))
			return card.getGift().getBase_info();
		else 
			throw new RuntimeException("类型错误");
	}
}
