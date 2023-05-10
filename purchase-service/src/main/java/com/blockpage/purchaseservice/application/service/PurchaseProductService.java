package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.PurchaseInPortDto;
import com.blockpage.purchaseservice.application.port.in.PurchaseProductUseCase;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
import com.blockpage.purchaseservice.application.port.out.SavePurchasePort;
import com.blockpage.purchaseservice.domain.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseProductService implements PurchaseProductUseCase {

    private final SavePurchasePort savePurchasePort;

    @Override
    public Long purchaseProduct(PurchaseInPortDto purchaseInPortDto) {

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
