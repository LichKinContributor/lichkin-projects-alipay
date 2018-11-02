package com.lichkin.application.bean.in.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AlipaySCCMTPayIn {

	/** 商户订单号 */
	private final String out_trade_no;

	/** 商户授权token */
	private final String authToken;

	/** 支付场景 */
	private String scene = "bar_code";

	/** 付款条码 */
	private final String auth_code;

	/** 每个商户的PID */
	private final String seller_id;

	/** 订单标题 */
	private final String subject;

	/** 订单总金额 */
	private final String total_amount;

	/** 支付超时时间 */
	private final String timeout_express;

	/** 公司ID */
	private final String compId;

	/** 订单类型 */
	private final String orderType;

}
