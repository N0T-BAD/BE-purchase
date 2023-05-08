package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.externalsystem.KakaoPayFeignClient;
import com.blockpage.purchaseservice.adaptor.externalsystem.apispec.KakaoPayReadyResponse;
import com.blockpage.purchaseservice.adaptor.externalsystem.apispec.Params;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.PurchaseInPortDto;
import com.blockpage.purchaseservice.application.port.in.PurchaseProductUseCase;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
import com.blockpage.purchaseservice.application.port.out.SavePurchasePort;
import com.blockpage.purchaseservice.domain.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseProductService implements PurchaseProductUseCase {

    private final SavePurchasePort savePurchasePort;
    private final KakaoPayFeignClient kakaoPayFeignClient;

    @Override
    public Long purchaseProduct(PurchaseInPortDto purchaseInPortDto) {

        Params params = Params.builder()
            .cid("TC0ONETIME")
            .partner_order_id("1")
            .partner_user_id("1")
            .item_name("1")
            .tax_free_amount("1")
            .total_amount("1")
            .quantity("1")
            .approval_url("http://localhost:8080/v1/purchases/kakaopay")
            .cancel_url("http://localhost:8080/v1/purchases/kakaopay")
            .fail_url("http://localhost:8080/v1/purchases/kakaopay")
            .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK b5eab0583f764211dca02952c891b3ba");
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        KakaoPayReadyResponse go = kakaoPayFeignClient.ready(httpHeaders, params);
        System.out.println("go = " + go.getNext_redirect_pc_url());

        PersistType persistType = PersistType.findPersistTypeByValue(purchaseInPortDto.getPersistType());
        ProductType productType = ProductType.findPersistTypeByValue(purchaseInPortDto.getProductType());
        Purchase purchase = new Purchase(productType, persistType);
        purchase.makeExpiredDate(persistType);

        switch (purchase.getProductType()) {
            case NFT -> {
                return savePurchasePort.saveNft(
                    new PurchaseOutPortDto(purchaseInPortDto.getMemberId(), purchase.getPersistType(), purchase.getExpiredDate()));
            }
            case EPISODE_BM -> {
                return savePurchasePort.saveEpisodeBM(
                    new PurchaseOutPortDto(purchaseInPortDto.getMemberId(), purchaseInPortDto.getEpisodeId(),
                        purchaseInPortDto.getWebtoonId(),
                        purchase.getPersistType(), purchase.getExpiredDate()));
            }
            case PROFILE_SKIN -> {
                return savePurchasePort.saveProfileSkin(
                    new PurchaseOutPortDto(purchaseInPortDto.getMemberId(), purchase.getPersistType(), purchase.getExpiredDate()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
    }
}
