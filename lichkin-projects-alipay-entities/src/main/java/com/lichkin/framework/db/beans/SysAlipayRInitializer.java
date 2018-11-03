package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysAlipayRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAlipaySCCMTLogEntity", "T_SYS_ALIPAY_SCCMTLOG", "SysAlipaySCCMTLogEntity");
		LKDBResource.addColumn("61000000", "SysAlipaySCCMTLogEntity", "id");
		LKDBResource.addColumn("61000001", "SysAlipaySCCMTLogEntity", "usingStatus");
		LKDBResource.addColumn("61000002", "SysAlipaySCCMTLogEntity", "insertTime");
		LKDBResource.addColumn("61000003", "SysAlipaySCCMTLogEntity", "compId");
		LKDBResource.addColumn("61000004", "SysAlipaySCCMTLogEntity", "orderType");
		LKDBResource.addColumn("61000005", "SysAlipaySCCMTLogEntity", "outTradeNo");
		LKDBResource.addColumn("61000006", "SysAlipaySCCMTLogEntity", "totalAmount");
		LKDBResource.addColumn("61000007", "SysAlipaySCCMTLogEntity", "sellerId");
		LKDBResource.addColumn("61000008", "SysAlipaySCCMTLogEntity", "subject");
		LKDBResource.addColumn("61000009", "SysAlipaySCCMTLogEntity", "requestJson");
		LKDBResource.addColumn("61000010", "SysAlipaySCCMTLogEntity", "tradeStatus");
		LKDBResource.addColumn("61000011", "SysAlipaySCCMTLogEntity", "updateTime");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAlipayAccountEntity", "T_SYS_ALIPAY_ACCOUNT", "SysAlipayAccountEntity");
		LKDBResource.addColumn("61001000", "SysAlipayAccountEntity", "id");
		LKDBResource.addColumn("61001001", "SysAlipayAccountEntity", "usingStatus");
		LKDBResource.addColumn("61001002", "SysAlipayAccountEntity", "insertTime");
		LKDBResource.addColumn("61001003", "SysAlipayAccountEntity", "compId");
		LKDBResource.addColumn("61001004", "SysAlipayAccountEntity", "accountNo");
		LKDBResource.addColumn("61001005", "SysAlipayAccountEntity", "authToken");
	}

}