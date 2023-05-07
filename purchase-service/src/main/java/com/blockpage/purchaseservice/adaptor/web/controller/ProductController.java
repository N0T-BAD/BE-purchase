package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.episodeBMStatus;
import com.blockpage.purchaseservice.adaptor.web.apispec.ApiWrapperResponse;
import com.blockpage.purchaseservice.adaptor.web.view.ProductsDetailView;
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
@RequestMapping("/v1/products")
public class ProductController {


    /***
     * 모든 NFT 상품 조회 API              | /v1/products?type=nft
     * 해당 에피소드 유료 BM 상품 조회 API   | /v1/products?type=profile-skin
     * 모든 프로필 스킨 상품 조회 API       | /v1/products?type=episode-bm
     */
    @GetMapping
    public ResponseEntity<ApiWrapperResponse> getAllProducts(@RequestParam String type) {

        List<ProductsDetailView> productsDetailViews = new ArrayList<>();

        switch (type) {
            case "nft": {
                productsDetailViews.add(new ProductsDetailView(1L, 1L, "NFT이름", "조석 작가님의 작품을 1BLOCK할인 받을 수 있습니다.", 1000,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR));
                productsDetailViews.add(new ProductsDetailView(2L, 2L, "NFT이름", "조석 작가님의 작품을 1BLOCK할인 받을 수 있습니다.", 1000,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    NftType.ONE_BLOCK_DISCOUNT_BY_AUTHOR));
                productsDetailViews.add(new ProductsDetailView(3L, 3L, "NFT이름", "조석 작가님의 작품을 2BLOCK할인 받을 수 있습니다.", 2000,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp",
                    NftType.TWO_BLOCK_DISCOUNT_BY_AUTHOR));
            }
            break;
            case "profile-skin": {
                productsDetailViews.add(new ProductsDetailView(1L, "플레티넘 테두리", "한달 동안 출석체크를 하면 받을 수 있습니다.", 5000,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));
                productsDetailViews.add(new ProductsDetailView(2L, "골드 테두리", "2주동안 출석체크를 하면 받을 수 있습니다.", 2000,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));
                productsDetailViews.add(new ProductsDetailView(3L, "실버 테두리", "회원가입시 받을 수 있습니다.", 200,
                    "https://i.namu.wiki/i/xWl9taYDaPvw2szKR9xbwBRYmVS8C7yvb4sn0jEKhaadR7OcKt_Voaz7gLEWlXq2Ji_s-T2WDjhMKs2uVSOM2drDi7tFTo0kkH7k-xwHrrKYT02zurwVXCYfVbyzePYOgdEpExfdPGS0PJFYEciiRA.webp"));

            }
            break;
            case "episode-bm": {
                productsDetailViews.add(new ProductsDetailView(1L, 1L, 1L, 2, episodeBMStatus.ON_SALE));
                productsDetailViews.add(new ProductsDetailView(2L, 1L, 2L, 2, episodeBMStatus.ON_SALE));
                productsDetailViews.add(new ProductsDetailView(3L, 1L, 3L, 2, episodeBMStatus.ON_SALE));
            }
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(productsDetailViews));
    }
}
