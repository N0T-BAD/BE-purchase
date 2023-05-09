package com.blockpage.purchaseservice.adaptor.external.kakao.apispec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class KakaoPayReadyParams {
    private String cid;
    private Integer tax_free_amount;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private Integer quantity;
    private Integer total_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;
}
