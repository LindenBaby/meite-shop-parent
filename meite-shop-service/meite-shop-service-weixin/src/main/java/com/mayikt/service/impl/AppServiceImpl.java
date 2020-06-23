package com.mayikt.service.impl;

import com.mayikt.weixin.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mayikt.service.AppService;

@RestController
public class AppServiceImpl implements AppService {

	@GetMapping("/getApp")
	public AppEntity getApp() {
		return new AppEntity("644064779", "yushengjun644");
	}

}
