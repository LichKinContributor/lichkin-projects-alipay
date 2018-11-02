package com.lichkin.application.services;

import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;
import com.lichkin.application.bean.out.impl.AlipayOpenAuthTokenOut;

/**
 * 支付宝权限服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface AlipayOpenAuthTokenService {

	/**
	 * 获取令牌
	 * @param in 入参
	 * @return 出参
	 */
	AlipayOpenAuthTokenOut getOpenAuthToken(AlipayOpenAuthTokenIn in);

}
