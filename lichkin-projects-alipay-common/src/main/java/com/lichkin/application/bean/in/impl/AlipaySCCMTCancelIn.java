package com.lichkin.application.bean.in.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AlipaySCCMTCancelIn {

	/** 商户订单号 */
	private final String out_trade_no;

	/** 商户授权token */
	private final String authToken;

}
