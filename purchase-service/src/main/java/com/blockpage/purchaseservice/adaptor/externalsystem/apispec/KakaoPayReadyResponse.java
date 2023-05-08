package com.blockpage.purchaseservice.adaptor.externalsystem.apispec;

import lombok.Getter;

@Getter
public class KakaoPayReadyResponse {
    private String tid;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String created_at;
}
