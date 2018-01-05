package me.hao0.wechat.model.store;

import lombok.Data;

/**
 * 创建门店实体类
 * @author zJun
 * @date 2017年12月29日 上午11:02:08
 */
@Data
public class Business {

	private Content business;
	
	@Data
	public static class Content {
		private BaseInfo base_info;
	}
}
