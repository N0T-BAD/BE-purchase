package com.blockpage.purchaseservice.adaptor.web.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.adaptor.web.apispec.ApiWrapperResponse;

import com.blockpage.purchaseservice.adaptor.web.view.MemberPurchaseView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        List<MemberPurchaseView> memberPurchaseViews = new ArrayList<>();
        switch (type) {
            case "nft": {
                memberPurchaseViews.add(new MemberPurchaseView(1L, "조석 작가 할인권", NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));
                memberPurchaseViews.add(new MemberPurchaseView(2L, "박형석 작가 할인권", NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));
                memberPurchaseViews.add(new MemberPurchaseView(3L, "정승훈 작가 할인권", NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));
            }
            break;
            case "profile-skin": {
                memberPurchaseViews.add(new MemberPurchaseView(1L, "플레티넘 테두리",
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    true));
                memberPurchaseViews.add(new MemberPurchaseView(2L, "골드 테두리",
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    false));
                memberPurchaseViews.add(new MemberPurchaseView(3L, "실버 테두리",
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    false));
            }
            break;
            case "episode-bm": {
                memberPurchaseViews.add(new MemberPurchaseView(1L, 50L, PersistType.PERMANENT, LocalDateTime.now()));
                memberPurchaseViews.add(new MemberPurchaseView(2L, 220L, PersistType.RENTAL, LocalDateTime.now()));
                memberPurchaseViews.add(new MemberPurchaseView(3L, 133L, PersistType.RENTAL, LocalDateTime.now()));
                memberPurchaseViews.add(new MemberPurchaseView(4L, 11L, PersistType.FREE, LocalDateTime.now()));
            }
            break;
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(memberPurchaseViews));
    }
}
