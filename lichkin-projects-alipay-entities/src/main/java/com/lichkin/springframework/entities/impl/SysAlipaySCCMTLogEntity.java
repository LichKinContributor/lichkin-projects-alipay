package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.enums.impl.AlipaySCCMTStatusEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付宝扫码收款交易请求日志表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAlipaySCCMTLogEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 30001L;

	/** 订单类型（字典） */
	@Column(length = 64, nullable = false)
	private String orderType;

	/** 唯一订单号 */
	@Column(length = 64, nullable = false)
	private String outTradeNo;

	/** 订单总金额，单位为元，精确到小数点后2位，不能超过1亿元 */
	@Column(length = 12)
	private String totalAmount;

	/** 卖家支付宝账号ID */
	@Column(length = 50)
	private String sellerId;

	/** 订单标题 */
	@Column(length = 30)
	private String subject;

	/** 订单json串 */
	@Column(length = 2000)
	private String requestJson;

	/** 交易状态 */
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private AlipaySCCMTStatusEnum tradeStatus;

	/** 修改时间（yyyyMMddHHmmssSSS） */
	@Column(length = 17)
	private String updateTime;

}
