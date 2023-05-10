package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.PurchaseInDto;
import com.blockpage.purchaseservice.application.port.in.PurchaseProductUseCase;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutDto;
import com.blockpage.purchaseservice.application.port.out.SavePurchasePort;
import com.blockpage.purchaseservice.domain.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService implements PurchaseProductUseCase {

    private final SavePurchasePort savePurchasePort;

    @Override
    public Long purchaseProduct(PurchaseInDto purchaseInDto) {

        PersistType persistType = PersistType.findPersistTypeByValue(purchaseInDto.getPersistType());
        ProductType productType = ProductType.findPersistTypeByValue(purchaseInDto.getProductType());
        Purchase purchase = new Purchase(productType, persistType);
        purchase.makeExpiredDate(persistType);

        switch (purchase.getProductType()) {
            case NFT -> {
                return savePurchasePort.saveNft(
                    new PurchaseOutDto(purchaseInDto.getMemberId(), purchase.getPersistType(), purchase.getExpiredDate()));
            }
            case EPISODE_BM -> {
                return savePurchasePort.saveEpisodeBM(
                    new PurchaseOutDto(purchaseInDto.getMemberId(), purchaseInDto.getEpisodeId(),
                        purchaseInDto.getWebtoonId(),
                        purchase.getPersistType(), purchase.getExpiredDate()));
            }
            case PROFILE_SKIN -> {
                return savePurchasePort.saveProfileSkin(
                    new PurchaseOutDto(purchaseInDto.getMemberId(), purchase.getPersistType(), purchase.getExpiredDate()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
    }
}
