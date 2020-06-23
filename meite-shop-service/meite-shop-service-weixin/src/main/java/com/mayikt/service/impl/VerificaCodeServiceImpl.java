package com.mayikt.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.api.BaseApiService;
import com.mayikt.base.entity.BaseResponse;
import com.mayikt.constants.Constants;
import com.mayikt.service.VerificaCodeService;
import com.mayikt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificaCodeServiceImpl extends BaseApiService<JSONObject> implements VerificaCodeService {
	@Autowired
	private RedisUtil redisUtil;

	/**
	 *  我们在做注册的时候  调用的是哪个项目？
	 *  会员服务     微信服务项目为什么不用   微服务
	 *
	 *  我在注册请求是   是不是直接调用会员服务
	 *  那可不可以直接在会员服务里面去验证注册码是否争取？
	 *  验证码是存在redis里面  哪个项目都能拿到值
	 *  各司其职   微信服务专门解决微信里面的操作
	 *  代码重用率会比较高
	 *
	 *  会员服务里面的代码没有写   交给大家去写    
	 *
	 *
	 * @param phone
	 * @param weixinCode
	 * @return
	 */
	@Override
	public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (StringUtils.isEmpty(weixinCode)) {
			return setResultError("注册码不能为空!");
		}
		String code = redisUtil.getString(Constants.WEIXINCODE_KEY + phone);
		if (StringUtils.isEmpty(code)) {
			return setResultError("注册码已经过期,请重新发送验证码");
		}
		if (!code.equals(weixinCode)) {
			return setResultError("注册码不正确");
		}

		return setResultSuccess("注册码验证码正确");
	}

}
