package com.lichkin.application.bean.in.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlipayOpenAuthTokenIn {

	/** 授权码(与refresh_token二选一，用户对应用授权后得到，授权后获取到的app_auth_code值) */
	private String app_auth_code;

	/** 公司ID */
	private String compId;

}
