package com.lichkin.application.services;

import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;

/**
 * 支付宝服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface AlipayService {

	/**
	 * 请求
	 * @param request 请求对象
	 * @return 响应对象
	 */
	AlipayResponse getResponse(AlipayRequest<?> request);

}
