package com.lichkin.application.services.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lichkin.application.bean.in.impl.AlipaySCCMTPayIn;
import com.lichkin.framework.defines.enums.impl.AlipaySCCMTStatusEnum;
import com.lichkin.framework.json.LKJsonUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysAlipaySCCMTLogEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysAlipaySCCMTLogService extends LKDBService {

	@Transactional
	public SysAlipaySCCMTLogEntity save(AlipaySCCMTPayIn in) {
		SysAlipaySCCMTLogEntity entity = new SysAlipaySCCMTLogEntity();
		entity.setOutTradeNo(in.getOut_trade_no());
		entity.setSubject(in.getSubject());
		entity.setSellerId(in.getSeller_id());
		entity.setTotalAmount(in.getTotal_amount());
		entity.setRequestJson(LKJsonUtils.toJson(in));
		entity.setOrderType(in.getOrderType());
		entity.setCompId(in.getCompId());
		dao.persistOne(entity);
		return entity;
	}


	@Transactional
	public void finish(SysAlipaySCCMTLogEntity entity, AlipaySCCMTStatusEnum tradeStatus) {
		entity.setTradeStatus(tradeStatus);
		entity.setUpdateTime(LKDateTimeUtils.now());
		dao.mergeOne(entity);
	}

}
