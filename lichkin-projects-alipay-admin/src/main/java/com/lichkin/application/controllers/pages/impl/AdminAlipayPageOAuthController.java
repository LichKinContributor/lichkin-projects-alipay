package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;
import com.lichkin.application.services.AlipayOpenAuthTokenService;
import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/admin")
public class AdminAlipayPageOAuthController extends LKPagesController {

	@Autowired
	private AlipayOpenAuthTokenService service;

	@Value("${com.lichkin.alipay.appid}")
	private String appid;

	private static final String openAuthTokenUrl = "https://openauth.alipay.com/oauth2/appToAppAuth.htm?";


	@WithoutLogin
	@GetMapping("/alipay/OAuth" + MAPPING)
	public LKPage linkToOAuth(AlipayOpenAuthTokenIn in) {
		LKPage mv = new LKPage();
		try {
			service.getOpenAuthToken(in);
			mv.putAttribute("openAuthResult", 0);
		} catch (Exception e) {
			String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			String url = openAuthTokenUrl + "app_id=" + appid + "&redirect_uri=" + currentUrl + "/admin/alipay/OAuth" + MAPPING + "?compId=" + in.getCompId();
			mv.putAttribute("openAuthUrl", url);
			mv.putAttribute("openAuthResult", 1);
		}
		return mv;
	}

}
