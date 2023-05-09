package com.blockpage.purchaseservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoPayReadyView {

    private String tid;
    private String next_redirect_pc_url;

    public KakaoPayReadyView(String tid, String next_redirect_pc_url) {
        this.tid = tid;
        this.next_redirect_pc_url = next_redirect_pc_url;
    }
}
