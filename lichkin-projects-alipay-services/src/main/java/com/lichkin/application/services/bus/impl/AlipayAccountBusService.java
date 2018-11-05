package com.lichkin.application.services.bus.impl;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAlipayAccountR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysAlipayAccountEntity;
import com.lichkin.springframework.services.LKDBService;

/**
 * 支付宝账户服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class AlipayAccountBusService extends LKDBService {

	public SysAlipayAccountEntity findAlipayAccount(String compId) {
		QuerySQL sql = new QuerySQL(false, SysAlipayAccountEntity.class);
		sql.eq(SysAlipayAccountR.compId, compId);
		sql.eq(SysAlipayAccountR.usingStatus, LKUsingStatusEnum.USING);
		return dao.getOne(sql, SysAlipayAccountEntity.class);
	}

}
