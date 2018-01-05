package me.hao0.wechat.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信异常，微信服务器返回错误时抛出的异常
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 5/11/15
 *
 * @since 1.0.0
 */
public class WechatException extends RuntimeException {

	private static final long serialVersionUID = 1895978005123791544L;
	
	private static Map<Integer, String> tips = new HashMap<Integer, String>();
	
	static {
		
		tips.put(-1, "系统错误，请稍后重试");
		tips.put(42001, "无效的access_token");
		tips.put(40009, "图片大小为0或者超过1M");
		tips.put(40097, "参数不正确，请参考字段要求检查json 字段");
		tips.put(65104, "门店的类型不合法，必须严格按照附表的分类填写");
		tips.put(65105, "图片url 不合法，必须使用接口1 的图片上传接口所获取的url");
		tips.put(65106, "门店状态必须未审核通过");
		tips.put(65107, "扩展字段为不允许修改的状态");
		tips.put(65109, "门店名为空");
		tips.put(65110, "门店所在详细街道地址为空");
		tips.put(65111, "门店的电话为空");
		tips.put(65112, "门店所在的城市为空");
		tips.put(65113, "门店所在的省份为空");
		tips.put(65114, "图片列表为空");
		tips.put(65115, "不正确");
		
		tips.put(40013, "不合法的Appid，请开发者检查AppID的正确性，避免异常字符，注意大小写。");
		tips.put(40053, "不合法的actioninfo，请开发者确认参数正确。");
		tips.put(40071, "不合法的卡券类型。");
		tips.put(40072, "不合法的编码方式。");
		tips.put(40078, "不合法的卡券状态。");
		tips.put(40079, "不合法的时间。");
		tips.put(40080, "不合法的CardExt。");
		tips.put(40099, "卡券已被核销。");
		tips.put(40100, "不合法的时间区间。");
		tips.put(40116, "不合法的Code码。");
		tips.put(40122, "不合法的库存数量。");
		tips.put(40124, "会员卡设置查过限制的 custom_field字段。");
		tips.put(40127, "卡券被用户删除或转赠中。");
		tips.put(41012, "缺少cardid参数。");
		tips.put(45030, "该cardid无接口权限。");
		tips.put(45031, "库存为0。");
		tips.put(45033, "用户领取次数超过限制get_limit");
		tips.put(41011, "缺少必填字段。");
		tips.put(45021, "字段超过长度限制，请参考相应接口的字段说明。");
		tips.put(40056, "不合法的Code码。");
		tips.put(43009, "自定义SN权限，请前往公众平台申请。");
		tips.put(43010, "无储值权限，请前往公众平台申请。");
	}
	
	/**
     * 微信返回的errcode
     */
    private Integer code;
    
    /**
     * 返回中文提示异常
     * @param errMap
     * @author zJun
     * @date 2018年1月3日 下午2:04:34
     */
    public static WechatException getInstance(Map<String, ?> errMap) {
    	Integer code = (Integer) errMap.get("errcode");
    	String message = tips.get(code);
    	if(message != null) 
    		return new WechatException(message);
    	else
    		return new WechatException(errMap);
    }

    public WechatException(Map<String, ?> errMap) {
    	super("[" + errMap.get("errcode") + "]" + errMap.get("errmsg"));
    	code = (Integer) errMap.get("errcode");
    }

    public WechatException() {
        super();
    }

    public WechatException(String message) {
        super(message);
    }

    public WechatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatException(Throwable cause) {
        super(cause);
    }

    protected WechatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return code;
    }
}
