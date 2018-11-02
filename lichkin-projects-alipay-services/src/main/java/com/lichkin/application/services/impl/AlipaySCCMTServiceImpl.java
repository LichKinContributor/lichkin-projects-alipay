package com.lichkin.application.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lichkin.application.bean.in.impl.AlipaySCCMTCancelIn;
import com.lichkin.application.bean.in.impl.AlipaySCCMTPayIn;
import com.lichkin.application.bean.in.impl.AlipaySCCMTQueryIn;
import com.lichkin.application.bean.out.impl.AlipaySCCMTCancelOut;
import com.lichkin.application.bean.out.impl.AlipaySCCMTPayOut;
import com.lichkin.application.bean.out.impl.AlipaySCCMTQueryOut;
import com.lichkin.application.services.AlipaySCCMTService;
import com.lichkin.defines.AlipayStatics;
import com.lichkin.framework.defines.enums.impl.AlipaySCCMTStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.json.LKJsonUtils;
import com.lichkin.springframework.entities.impl.SysAlipaySCCMTLogEntity;

@Service
public class AlipaySCCMTServiceImpl extends AlipayServiceImpl implements AlipaySCCMTService {

	@Autowired
	private SysAlipaySCCMTLogService alipayRequestLogBusService;


	@Override
	public AlipaySCCMTPayOut tradePay(AlipaySCCMTPayIn in) {
		String tradeNo = in.getOut_trade_no();// 商户订单号
		AlipaySCCMTPayOut out = new AlipaySCCMTPayOut(tradeNo);

		// 保存日志
		SysAlipaySCCMTLogEntity requestLogEntity = null;
		try {
			requestLogEntity = alipayRequestLogBusService.save(in);
		} catch (Exception e) {
			logger.error(e);
			out.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
			return out;
		}

		// 请求支付宝接口
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		request.putOtherTextParam("app_auth_token", in.getAuthToken());
		request.setBizContent(LKJsonUtils.toJson(in));
		AlipayResponse response = getResponse(request);
		if (response == null) {// 请求失败，可能是请求发送成功，但是响应时异常，也需要做查询确认订单状态。
			out.setTradeStatus(handleResultByLoopQuery(tradeNo, in.getAuthToken()));
		} else {// 请求成功
			switch (response.getCode()) {
				case AlipayStatics.SUCCESS_CODE:// 支付成功
					out.setTradeStatus(AlipaySCCMTStatusEnum.SUCCESS);
				break;
				case AlipayStatics.PAYING_CODE:// 支付中
				case AlipayStatics.ERROR_CODE:// 支付异常
					out.setTradeStatus(handleResultByLoopQuery(tradeNo, in.getAuthToken()));
				break;
				default:// 其他情况订单都是支付失败
					out.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
				break;
			}
		}

		// 修改流水单状态
		try {
			alipayRequestLogBusService.finish(requestLogEntity, out.getTradeStatus());
		} catch (Exception e) {
			logger.error(e);
			// TODO 需要开任务去修正状态
		}
		return out;
	}


	private AlipaySCCMTStatusEnum handleResultByLoopQuery(String tradeNo, String authToken) {
		AlipaySCCMTQueryOut queryOut = tradeQueryLoop(new AlipaySCCMTQueryIn(tradeNo, authToken));
		switch (queryOut.getTradeStatus()) {
			case SUCCESS:// 交易成功/交易结束
				return AlipaySCCMTStatusEnum.SUCCESS;
			case FAILED:// 交易关闭/交易失败
				return AlipaySCCMTStatusEnum.FAILED;
			case WAITING:// 等待付款
				tradeCancelLoop(new AlipaySCCMTCancelIn(tradeNo, authToken));
				return AlipaySCCMTStatusEnum.FAILED;
			case UNKNOWN:// 请求失败/查询失败/未知异常
				return AlipaySCCMTStatusEnum.UNKNOWN;// TODO 这地方有问题
			default:
				throw new LKRuntimeException(LKErrorCodesEnum.CONFIG_ERROR);// 补充枚举定义后此处需完善
		}
	}


	@Override
	public AlipaySCCMTQueryOut tradeQuery(AlipaySCCMTQueryIn queryIn) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent(LKJsonUtils.toJson(queryIn));
		request.putOtherTextParam("app_auth_token", queryIn.getAuthToken());
		AlipayResponse response = getResponse(request);
		if (response == null) {// 请求失败
			AlipaySCCMTQueryOut queryOut = new AlipaySCCMTQueryOut(null);
			queryOut.setTradeStatus(AlipaySCCMTStatusEnum.UNKNOWN);
			return queryOut;
		}

		AlipaySCCMTQueryOut queryOut = new AlipaySCCMTQueryOut((AlipayTradeQueryResponse) response);
		String tradeStatus = queryOut.getResponse().getTradeStatus();
		String code = response.getCode();
		switch (code) {
			case AlipayStatics.SUCCESS_CODE: {// 查询成功
				switch (tradeStatus) {
					case "TRADE_SUCCESS":// 交易成功
					case "TRADE_FINISHED":// 交易结束
						queryOut.setTradeStatus(AlipaySCCMTStatusEnum.SUCCESS);
					break;
					case "WAIT_BUYER_PAY":// 等待付款
						queryOut.setTradeStatus(AlipaySCCMTStatusEnum.WAITING);
					break;
					case "TRADE_CLOSED":// 交易关闭
						queryOut.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
					break;
					default:// 其他情况订单都是失败状态
						queryOut.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
					break;
				}
			}
			break;
			case AlipayStatics.ERROR_CODE: {// 查询失败
				queryOut.setTradeStatus(AlipaySCCMTStatusEnum.UNKNOWN);
			}
			break;
			default:// 未知异常
				queryOut.setTradeStatus(AlipaySCCMTStatusEnum.UNKNOWN);
			break;
		}
		return queryOut;
	}


	@Value("${com.lichkin.alipay.SCCMT.query.maxTimes:21}")
	private int queryMaxTimes;

	@Value("${com.lichkin.alipay.SCCMT.query.duration:3000}")
	private int queryDuration;


	@Override
	public AlipaySCCMTQueryOut tradeQueryLoop(AlipaySCCMTQueryIn queryIn) {
		for (int i = 0; i < queryMaxTimes; i++) {
			// 每隔一定时间调用一次
			try {
				Thread.sleep(queryDuration);
			} catch (InterruptedException e) {
				logger.error(e);
			}

			AlipaySCCMTQueryOut queryOut = tradeQuery(queryIn);
			switch (queryOut.getTradeStatus()) {
				case SUCCESS:// 交易成功/交易结束
				case FAILED:// 交易关闭/交易失败
					return queryOut;
				case WAITING:// 等待付款
					if (i == (queryMaxTimes - 1)) {
						return queryOut;
					}
				break;
				case UNKNOWN:// 请求失败/查询失败/未知异常
					if (i == (queryMaxTimes - 1)) {
						return queryOut;
					}
				break;
				default:
					throw new LKRuntimeException(LKErrorCodesEnum.CONFIG_ERROR);// 补充枚举定义后此处需完善
			}
		}
		return null;// 所有情况均捕获，此处不可能走到。仅为补充编译异常代码。
	}


	@Override
	public AlipaySCCMTCancelOut tradeCancel(AlipaySCCMTCancelIn cancelIn) {
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizContent(LKJsonUtils.toJson(cancelIn));
		request.putOtherTextParam("app_auth_token", cancelIn.getAuthToken());
		AlipayResponse response = getResponse(request);
		if (response == null) {// 请求失败
			AlipaySCCMTCancelOut cancelOut = new AlipaySCCMTCancelOut(null);
			cancelOut.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
			return cancelOut;
		}
		AlipaySCCMTCancelOut cancelOut = new AlipaySCCMTCancelOut((AlipayTradeCancelResponse) response);
		String code = cancelOut.getResponse().getCode();
		switch (code) {
			case AlipayStatics.SUCCESS_CODE: // 撤销成功
				cancelOut.setTradeStatus(AlipaySCCMTStatusEnum.SUCCESS);
			break;
			default:// 未知异常
				cancelOut.setTradeStatus(AlipaySCCMTStatusEnum.FAILED);
			break;
		}
		return cancelOut;
	}


	@Value("${com.lichkin.alipay.SCCMT.query.maxTimes:3}")
	private int cancelMaxTimes;

	@Value("${com.lichkin.alipay.SCCMT.query.duration:3000}")
	private int cancelDuration;


	@Override
	public AlipaySCCMTCancelOut tradeCancelLoop(AlipaySCCMTCancelIn cancelIn) {
		for (int i = 0; i < cancelMaxTimes; i++) {
			// 每隔一定时间调用一次
			try {
				Thread.sleep(cancelDuration);
			} catch (InterruptedException e) {
				logger.error(e);
			}

			AlipaySCCMTCancelOut cancelOut = tradeCancel(cancelIn);
			switch (cancelOut.getTradeStatus()) {
				case SUCCESS:
					return cancelOut;
				case FAILED:
					if (i == (cancelMaxTimes - 1)) {
						return cancelOut;
					} else {
						if ("N".equals(cancelOut.getResponse().getRetryFlag())) {
							return cancelOut;
						}
					}
				default:
			}
		}
		return null;// 所有情况均捕获，此处不可能走到。仅为补充编译异常代码。
	}

}
