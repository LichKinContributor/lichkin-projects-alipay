package com.lichkin.framework.defines.enums.impl;

/**
 * 支付宝扫码收款交易状态枚举
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public enum AlipaySCCMTStatusEnum {

	/** 业务交易明确成功，比如支付成功、退货成功 */
	SUCCESS,

	/** 等待买家付款 */
	WAITING,

	/** 业务交易明确失败，比如支付明确失败、退货明确失败、交易关闭 */
	FAILED,

	/**
	 * <pre>
	 * 支付接口：未知状态
	 * 查询接口：请求失败/查询失败/未知异常
	 * </pre>
	 */
	UNKNOWN,

}
