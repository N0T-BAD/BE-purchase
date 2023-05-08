package com.blockpage.purchaseservice.adaptor.externalsystem;

import com.blockpage.purchaseservice.adaptor.externalsystem.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.externalsystem.apispec.Params;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "purchase-service", url = "https://kapi.kakao.com/v1/payment/ready")
public interface KakaoPayFeignClient {

    @PostMapping
    KakaoPayReadyResponse ready(@RequestHeader HttpHeaders httpHeaders, @SpringQueryMap Params params);

}
