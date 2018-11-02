package com.lichkin.application.services.impl;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;
import com.lichkin.application.bean.out.impl.AlipayOpenAuthTokenOut;
import com.lichkin.application.services.AlipayOpenAuthTokenService;
import com.lichkin.framework.json.LKJsonUtils;

@Service
public class AlipayOpenAuthTokenServiceImpl extends AlipayServiceImpl implements AlipayOpenAuthTokenService {

	@Override
	public AlipayOpenAuthTokenOut getOpenAuthToken(AlipayOpenAuthTokenIn in) {
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizContent(LKJsonUtils.toJson(in));
		AlipayResponse response = getResponse(request);
		if (response == null) {
			return null;// TODO
		}
		return new AlipayOpenAuthTokenOut((AlipayOpenAuthTokenAppResponse) response);
	}

}
