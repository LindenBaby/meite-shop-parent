package com.mayikt.member.feign;

import com.mayikt.service.AppService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-mayikt-weixin")
public interface AppServiceFeign extends AppService {

}
