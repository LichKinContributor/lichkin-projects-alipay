package com.lichkin.application.bean.out.impl;

import com.lichkin.framework.defines.enums.impl.AlipaySCCMTStatusEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AlipaySCCMTPayOut {

	/** 交易状态 */
	private AlipaySCCMTStatusEnum tradeStatus;

	/** 商户订单号 */
	private final String outTradeNo;

}
