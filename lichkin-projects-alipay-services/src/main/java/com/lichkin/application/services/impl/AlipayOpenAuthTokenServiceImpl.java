package com.lichkin.application.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;
import com.lichkin.application.services.AlipayOpenAuthTokenService;
import com.lichkin.defines.AlipayStatics;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAlipayAccountR;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.json.LKJsonUtils;
import com.lichkin.springframework.entities.impl.SysAlipayAccountEntity;

@Service
public class AlipayOpenAuthTokenServiceImpl extends AlipayServiceImpl implements AlipayOpenAuthTokenService {

	@Override
	public void getOpenAuthToken(AlipayOpenAuthTokenIn in) {
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		Map<String, String> map = new HashMap<>();
		map.put("grant_type", "app_auth_code");
		map.put("code", in.getApp_auth_code());
		request.setBizContent(LKJsonUtils.toJson(map));
		try {
			AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
			if (AlipayStatics.SUCCESS_CODE.equals((response).getCode())) {
				String accountNo = response.getUserId();
				QuerySQL sql = new QuerySQL(SysAlipayAccountEntity.class);
				sql.eq(SysAlipayAccountR.compId, in.getCompId());
				sql.eq(SysAlipayAccountR.accountNo, accountNo);
				SysAlipayAccountEntity exist = dao.getOne(sql, SysAlipayAccountEntity.class);
				if (exist == null) {
					SysAlipayAccountEntity entity = new SysAlipayAccountEntity();
					entity.setAccountNo(accountNo);
					entity.setAuthToken(response.getAppAuthToken());
					dao.persistOne(entity);
				} else {
					exist.setAuthToken(response.getAppAuthToken());
					dao.mergeOne(exist);
				}
			}
		} catch (AlipayApiException e) {
		}
		throw new LKRuntimeException(LKErrorCodesEnum.PARAM_ERROR);
	}

}
