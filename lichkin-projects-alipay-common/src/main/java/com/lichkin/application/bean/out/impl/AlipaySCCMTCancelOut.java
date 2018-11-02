package com.lichkin.application.bean.out.impl;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.lichkin.framework.defines.enums.impl.AlipaySCCMTStatusEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AlipaySCCMTCancelOut {

	/** 交易状态 */
	private AlipaySCCMTStatusEnum tradeStatus;

	/** 响应详情 */
	private final AlipayTradeCancelResponse response;

}
