package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.external.block.request.BlockPayRequestParams;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService implements PurchaseUseCase {

    private final PurchasePersistencePort purchasePersistencePort;
    private final BlockServicePort blockServicePort;

    @Override
    public void purchaseProduct(PurchaseQuery query) {

        //블럭 결제
        BlockPayRequestParams blockPayRequestParams = BlockPayRequestParams.addEssentialParams(query.getBlockQuantity());
        blockServicePort.blockPay(blockPayRequestParams);

        //구매 내역 생성
        Purchase purchase = Purchase.initPurchaseForSave(query);
        purchase.makeExpiredDate(persistType);

        switch (purchase.getProductType()) {
            case NFT -> purchasePersistencePort.saveNft(purchase);
            case EPISODE_BM -> purchasePersistencePort.saveEpisodeBM(purchase);
            case PROFILE_SKIN -> purchasePersistencePort.saveProfileSkin(purchase);
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
    }

    @Getter
    @Setter
    public class PurchaseDto {

        //common
        private Long memberId;
        private PersistType persistType;
        private LocalDateTime expiredDate;

        //episodeBM
        private Long episodeId;
        private Long webtoonId;


        public PurchaseDto(Long memberId, PersistType persistType, LocalDateTime expiredDate) {
            this.memberId = memberId;
            this.persistType = persistType;
            this.expiredDate = expiredDate;
        }

        public PurchaseDto(Long memberId, Long episodeId, Long webtoonId, PersistType persistType, LocalDateTime expiredDate) {
            this.memberId = memberId;
            this.episodeId = episodeId;
            this.webtoonId = webtoonId;
            this.persistType = persistType;
            this.expiredDate = expiredDate;
        }
    }
}
