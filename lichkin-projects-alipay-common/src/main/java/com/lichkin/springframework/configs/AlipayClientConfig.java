package com.lichkin.springframework.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.lichkin.framework.utils.security.rsa.LKRSAReader;

@Configuration
public class AlipayClientConfig {

	@Value("${com.lichkin.alipay.openApiDomain}")
	private String openApiDomain;

	@Value("${com.lichkin.alipay.appid}")
	private String appid;

	@Value("${com.lichkin.alipay.alipayPublicKey}")
	private String alipayPublicKey;


	@Bean
	public AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(

				openApiDomain,

				appid,

				LKRSAReader.readPrimaryKey("/opt/security/alipay/rsa_private_key_pkcs8.pem"),

				"json", // format 仅支持JSON

				"UTF-8", // 请求使用的编码格式，如utf-8,gbk,gb2312等

				alipayPublicKey,

				"RSA2" // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2

		);
	}

}
