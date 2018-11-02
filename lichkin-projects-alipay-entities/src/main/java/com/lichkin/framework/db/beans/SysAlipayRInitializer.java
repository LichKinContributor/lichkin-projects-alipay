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
		LKDBResource.addColumn("30001000", "SysAlipaySCCMTLogEntity", "id");
		LKDBResource.addColumn("30001001", "SysAlipaySCCMTLogEntity", "usingStatus");
		LKDBResource.addColumn("30001002", "SysAlipaySCCMTLogEntity", "insertTime");
		LKDBResource.addColumn("30001003", "SysAlipaySCCMTLogEntity", "compId");
		LKDBResource.addColumn("30001004", "SysAlipaySCCMTLogEntity", "orderType");
		LKDBResource.addColumn("30001005", "SysAlipaySCCMTLogEntity", "outTradeNo");
		LKDBResource.addColumn("30001006", "SysAlipaySCCMTLogEntity", "totalAmount");
		LKDBResource.addColumn("30001007", "SysAlipaySCCMTLogEntity", "sellerId");
		LKDBResource.addColumn("30001008", "SysAlipaySCCMTLogEntity", "subject");
		LKDBResource.addColumn("30001009", "SysAlipaySCCMTLogEntity", "requestJson");
		LKDBResource.addColumn("30001010", "SysAlipaySCCMTLogEntity", "tradeStatus");
		LKDBResource.addColumn("30001011", "SysAlipaySCCMTLogEntity", "updateTime");
	}

}