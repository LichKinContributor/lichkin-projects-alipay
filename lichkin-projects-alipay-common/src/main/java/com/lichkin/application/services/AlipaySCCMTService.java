package com.lichkin.application.services;

import com.lichkin.application.bean.in.impl.AlipaySCCMTCancelIn;
import com.lichkin.application.bean.in.impl.AlipaySCCMTPayIn;
import com.lichkin.application.bean.in.impl.AlipaySCCMTQueryIn;
import com.lichkin.application.bean.out.impl.AlipaySCCMTCancelOut;
import com.lichkin.application.bean.out.impl.AlipaySCCMTPayOut;
import com.lichkin.application.bean.out.impl.AlipaySCCMTQueryOut;

/**
 * 支付宝扫码收款交易服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface AlipaySCCMTService {

	/**
	 * 支付订单
	 * @param in 入参
	 * @return 出参
	 */
	AlipaySCCMTPayOut tradePay(AlipaySCCMTPayIn in);


	/**
	 * 查询订单
	 * @param in 入参
	 * @return 出参
	 */
	AlipaySCCMTQueryOut tradeQuery(AlipaySCCMTQueryIn in);


	/**
	 * 查询订单（循环）
	 * @param in 入参
	 * @return 出参
	 */
	AlipaySCCMTQueryOut tradeQueryLoop(AlipaySCCMTQueryIn queryIn);


	/**
	 * 取消订单
	 * @param in 入参
	 * @return 出参
	 */
	AlipaySCCMTCancelOut tradeCancel(AlipaySCCMTCancelIn in);


	/**
	 * 取消订单（循环）
	 * @param in 入参
	 * @return 出参
	 */
	AlipaySCCMTCancelOut tradeCancelLoop(AlipaySCCMTCancelIn cancelIn);

}
