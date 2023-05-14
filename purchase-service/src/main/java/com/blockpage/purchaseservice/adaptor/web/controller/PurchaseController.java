package com.blockpage.purchaseservice.adaptor.web.controller;


import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.adaptor.web.view.ApiWrapperResponse;

import com.blockpage.purchaseservice.adaptor.web.requestbody.ProfileSkinRequest;
import com.blockpage.purchaseservice.adaptor.web.requestbody.PurchaseRequest;
import com.blockpage.purchaseservice.adaptor.web.view.MemberPurchaseView;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.PurchaseQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/purchases")
public class PurchaseController {

    private final PurchaseUseCase purchaseUseCase;

    private long TEST_MEMBER_ID = 1l;

    /**
     * Mock Data 작업중 (서비스 로직 없음)
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
                memberPurchaseViews.add(new MemberPurchaseView(4L, 11L, PersistType.RENTAL, LocalDateTime.now()));
            }
            break;
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(memberPurchaseViews));
    }

    /**
     * @param type = nft, profile-skin, episode-bm
     */
    @PostMapping
    public ResponseEntity<ApiWrapperResponse> postPurchases(@RequestParam("type") String type,
        @RequestBody PurchaseRequest purchaseRequest) {

        purchaseUseCase.purchaseProduct(PurchaseQuery.toQuery(TEST_MEMBER_ID, type, purchaseRequest));

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse("리소스가 정상적으로 생성되었습니다."));
    }

    @PatchMapping
    public ResponseEntity<ApiWrapperResponse> patchPurchasesProfileSkinDefault(@RequestBody ProfileSkinRequest profileSkinRequest) {
        //profile skin default 값 설정 변경 + DTO 매핑 함수
        System.out.println("profileSkinRequest = " + profileSkinRequest.getProfileSkinId());
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse("리소스가 정상적으로 변경되었습니다."));
    }

    @PostMapping("/kakaopay")
    public void please() {
        System.out.println("==========================================");
    }
}
