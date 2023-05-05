package com.blockpage.purchaseservice.adaptor.web.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.adaptor.web.apispec.ApiWrapperResponse;

import com.blockpage.purchaseservice.adaptor.web.view.MemberPurchaseView;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/purchases")
public class PurchaseController {

    /***
     *  해당 유저 NFT 구매 목록 조회 API                    |   /v1/purchases?type=nft
     *  해당 유저 프로필 스킨 구매 목록 조회 API              |   /v1/purchases?type=profile-skin
     *  해당 유저 에피소드 BM 구매 목록(히스토리) 조회 API     |   /v1/purchases?type=history
     *  해당 유저 에피소드 BM 구매 목록(유료) 조회 API        |   /v1/purchases?type=episode-bm-paid
     */
    @GetMapping
    public ResponseEntity<ApiWrapperResponse> getMemberPurchaseHistory(@RequestParam("type") String type) {
        MemberPurchaseView memberPurchaseView = null;
        switch (type) {
            case "nft": {
                memberPurchaseView = new MemberPurchaseView(1L, "조석 작가 할인권", NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp");
            }
            break;
            case "profile-skin": {
                memberPurchaseView = new MemberPurchaseView(1L, "플레티넘 테두리",
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    true);
            }
            break;
            case "episode-bm": {
                memberPurchaseView = new MemberPurchaseView(1L, 1L, PersistType.PERMANENT, LocalDateTime.now());
            }
            break;
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(memberPurchaseView));
    }
}
