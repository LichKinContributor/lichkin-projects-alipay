package com.lichkin.application.services;

import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;

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
	void getOpenAuthToken(AlipayOpenAuthTokenIn in);

}
