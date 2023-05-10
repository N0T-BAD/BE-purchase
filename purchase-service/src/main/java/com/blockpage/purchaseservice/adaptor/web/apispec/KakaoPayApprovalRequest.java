package com.blockpage.purchaseservice.adaptor.web.apispec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KakaoPayApprovalRequest {

    private String pgToken;
}
