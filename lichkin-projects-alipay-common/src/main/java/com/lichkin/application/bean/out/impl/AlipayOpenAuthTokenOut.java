package com.lichkin.application.bean.out.impl;

import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AlipayOpenAuthTokenOut {

	/** 响应详情 */
	private final AlipayOpenAuthTokenAppResponse response;

}
