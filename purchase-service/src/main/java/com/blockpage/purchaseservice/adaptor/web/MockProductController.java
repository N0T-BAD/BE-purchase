package com.blockpage.purchaseservice.adaptor.web;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.NftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.ProfileSkinRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.entity.WebtoonBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.WebtoonBMRepository;
import com.blockpage.purchaseservice.adaptor.web.view.NftDetailView;
import com.blockpage.purchaseservice.adaptor.web.view.ProfileSkinDetailView;
import com.blockpage.purchaseservice.adaptor.web.view.WebtoonBMDetailView;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/products")
public class MockProductController {

    private final NftRepository nftRepository;
    private final ProfileSkinRepository profileSkinRepository;
    private final WebtoonBMRepository webtoonBMRepository;

    /***
     * 모든 NFT 상품 조회 API              | /v1/products?type=nft
     * 해당 에피소드 유료 BM 상품 조회 API   | /v1/products?type=profile-skin
     * 모든 프로필 스킨 상품 조회 API       | /v1/products?type=episode-bm
     */
    @GetMapping
    public ResponseEntity getAllProducts(@RequestParam String type) {
        Long webtoonId = 1L;

        switch (type) {
            case "nft": {
                List<NftEntity> nftProducts = nftRepository.findAll();
                List<NftDetailView> result = nftProducts.stream()
                    .map(NftDetailView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            case "profile-skin": {
                List<ProfileSkinEntity> profileSkins = profileSkinRepository.findAll();
                List<ProfileSkinDetailView> result = profileSkins.stream()
                    .map(ProfileSkinDetailView::toViewFromEntity)
                    .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK)
                    .body(result);
            }
            case "episode-bm": {
                List<WebtoonBMEntity> webtoonBMs = webtoonBMRepository.findAllByWebtoonId(webtoonId);
                List<WebtoonBMDetailView> result = webtoonBMs.stream()
                    .map(WebtoonBMDetailView::toViewFromEntity)
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

}
