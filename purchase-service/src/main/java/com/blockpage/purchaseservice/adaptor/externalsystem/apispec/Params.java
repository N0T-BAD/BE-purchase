package com.blockpage.purchaseservice.adaptor.externalsystem.apispec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Params {

    private String cid;
    private String tax_free_amount;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private String quantity;
    private String total_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;

}
