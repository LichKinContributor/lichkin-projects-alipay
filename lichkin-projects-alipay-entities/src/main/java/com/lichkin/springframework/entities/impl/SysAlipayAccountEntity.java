package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 公司支付宝账户信息表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAlipayAccountEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 61001L;

	/** 账号 */
	@Column(length = 32, nullable = false)
	private String accountNo;

	/** 商户token */
	@Column(length = 64, nullable = false)
	private String authToken;

}
