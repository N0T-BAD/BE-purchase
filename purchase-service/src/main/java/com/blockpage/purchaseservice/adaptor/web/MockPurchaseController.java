package com.blockpage.purchaseservice.adaptor.web;

import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasNftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasProfileSkinRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasWebtoonBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.MemberHasWebtoonBMRepository;

import com.blockpage.purchaseservice.adaptor.infrastructure.PersistType;
import com.blockpage.purchaseservice.adaptor.web.view.MemberNftView;
import com.blockpage.purchaseservice.adaptor.web.view.MemberProfileSkinView;
import com.blockpage.purchaseservice.adaptor.web.view.MemberWebtoonBMView;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/purchase")
public class MockPurchaseController {

    private final MemberHasNftRepository memberHasNFtRepository;
    private final MemberHasWebtoonBMRepository memberHasWebtoonBMRepository;
    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;

    /***
     *  해당 유저 NFT 구매 목록 조회 API                    |   /v1/purchases?type=nft
     *  해당 유저 프로필 스킨 구매 목록 조회 API              |   /v1/purchases?type=profile-skin
     *  해당 유저 에피소드 BM 구매 목록(히스토리) 조회 API     |   /v1/purchases?type=history
     *  해당 유저 에피소드 BM 구매 목록(유료) 조회 API        |   /v1/purchases?type=episode-bm-paid
     */

    @GetMapping
    public ResponseEntity paymentHistory(@RequestParam("type") String type) {
        Long memberId = 1L;
        switch (type) {
            case "nft": {
                List<MemberHasNftEntity> memberNfts = memberHasNFtRepository.findByMemberId(memberId);
                List<MemberNftView> result = memberNfts.stream()
                    .map(MemberNftView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            case "profile-skin": {
                List<MemberHasProfileSkinEntity> memberProfileSkins = memberHasProfileSkinRepository.findByMemberId(memberId);
                List<MemberProfileSkinView> result = memberProfileSkins.stream()
                    .map(MemberProfileSkinView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            case "history": {
                List<MemberHasWebtoonBMEntity> memberHasWebtoonBMs = memberHasWebtoonBMRepository.findByMemberId(memberId);
                List<MemberWebtoonBMView> result = memberHasWebtoonBMs.stream()
                    .map(MemberWebtoonBMView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            case "episode-bm-paid": {
                List<MemberHasWebtoonBMEntity> memberHasWebtoonBMs = memberHasWebtoonBMRepository.findByMemberId(memberId);
                List<MemberWebtoonBMView> result = memberHasWebtoonBMs.stream()
                    .filter(bm -> (bm.getPersistType() == PersistType.PERMANENT) || (bm.getPersistType() == PersistType.RENTAL))
                    .map(MemberWebtoonBMView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            default: {
                return ResponseEntity.status(HttpStatus.OK)
                    .body("잘못된 요청 입니다.");
            }
        }
    }

    @GetMapping("/test")
    public void test(
        @RequestParam(value = "weekday", required = false) String weekday,
        @RequestParam(value = "genre", required = false) String genre
    ) {
        System.out.println("data = " + weekday);
        System.out.println("data.getGenre() = " + genre);
    }
}
