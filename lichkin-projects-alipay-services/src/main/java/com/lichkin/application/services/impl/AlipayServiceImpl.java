package com.lichkin.application.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.lichkin.application.services.AlipayService;
import com.lichkin.springframework.services.LKDBService;

class AlipayServiceImpl extends LKDBService implements AlipayService {

	@Autowired
	protected AlipayClient alipayClient;


	@Override
	public AlipayResponse getResponse(AlipayRequest<?> request) {
		try {
			return alipayClient.execute(request);
		} catch (AlipayApiException e) {
			logger.error(e);
			return null;
		}
	}

}
