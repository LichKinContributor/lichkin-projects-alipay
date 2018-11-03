package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.application.bean.in.impl.AlipayOpenAuthTokenIn;
import com.lichkin.application.services.AlipayOpenAuthTokenService;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/admin")
public class AdminAlipayPageOAuthController extends LKPagesController {

	@Autowired
	private AlipayOpenAuthTokenService service;


	@GetMapping("/alipay/OAuth" + MAPPING)
	public LKPage linkToOAuth(AlipayOpenAuthTokenIn in) {
		LKPage mv = new LKPage();
		try {
			service.getOpenAuthToken(in);
			mv.putAttribute("result", 0);
		} catch (Exception e) {
			mv.putAttribute("result", 1);
		}
		return mv;
	}

}
