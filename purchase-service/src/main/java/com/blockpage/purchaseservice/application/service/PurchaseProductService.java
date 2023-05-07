package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.application.port.in.PurchaseInPortDto;
import com.blockpage.purchaseservice.application.port.in.PurchaseProductUseCase;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
import com.blockpage.purchaseservice.application.port.out.SavePurchasePort;
import java.time.LocalDateTime;
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
        ;
        /*
        도메인 로직 작성 ! 지금은 살짞 패스!
         */

        switch (purchaseInPortDto.getType()) {
            case "nft":
                return savePurchasePort.saveNft(new PurchaseOutPortDto(purchaseInPortDto.getMemberId()));
            case "episode-bm":
                return savePurchasePort.saveEpisodeBM(new PurchaseOutPortDto(
                    purchaseInPortDto.getMemberId(), purchaseInPortDto.getEpisodeId(), purchaseInPortDto.getWebtoonId(),
                    PersistType.findPersistTypeByValue(purchaseInPortDto.getPersistType()), LocalDateTime.now()));
            case "profile-skin":
                return savePurchasePort.saveProfileSkin(new PurchaseOutPortDto(purchaseInPortDto.getMemberId()));
            default:
                return 999L;
        }
    }
}
