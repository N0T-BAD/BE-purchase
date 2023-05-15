package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.external.block.request.BlockPayRequestParams;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort.MemberHasEpisodeBMEntityDto;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort.MemberHasNftEntityDto;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort.MemberHasProfileSkinEntityDto;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseService implements PurchaseUseCase {

    private final PurchasePersistencePort purchasePersistencePort;
    private final BlockServicePort blockServicePort;

    @Override
    @Transactional
    public void purchaseProduct(PurchaseQuery query) {

        BlockPayRequestParams blockPayRequestParams = BlockPayRequestParams.addEssentialParams(query.getBlockQuantity());
        blockServicePort.blockPay(blockPayRequestParams);

        Purchase purchase = Purchase.initPurchaseForSave(query);

        switch (purchase.getProductType()) {
            case NFT -> purchasePersistencePort.saveNft(purchase);
            case EPISODE_BM -> purchasePersistencePort.saveEpisodeBM(purchase);
            case PROFILE_SKIN -> purchasePersistencePort.saveProfileSkin(purchase);
            default -> throw new IllegalStateException("Unexpected value: " + purchase.getProductType());
        }
    }

    @Override
    public PurchaseDto purchaseQuery(FindPurchaseQuery findPurchaseQuery) {
        switch (ProductType.findByValue(findPurchaseQuery.getProductType())) {
            case NFT -> {
                List<MemberHasNftEntityDto> nft = purchasePersistencePort.findNft(findPurchaseQuery.getMemberId());
            }
            case EPISODE_BM -> {
                List<MemberHasEpisodeBMEntityDto> episodeBM = purchasePersistencePort.findEpisodeBMByWebtoonId(findPurchaseQuery.getMemberId(),
                    findPurchaseQuery.getWebtoonId());
            }
            case PROFILE_SKIN -> {
                List<MemberHasProfileSkinEntityDto> profileSkin = purchasePersistencePort.findProfileSkin(findPurchaseQuery.getMemberId());
            }
            default -> throw new IllegalStateException("Unexpected value: " + ProductType.findByValue(findPurchaseQuery.getProductType()));
        }
        return null;
    }

    @Getter
    @AllArgsConstructor
    public class PurchaseDto {

        private Long memberId;
        private PersistType persistType;
        private LocalDateTime expiredDate;

        private Long memberHasEpisodeBMId;
        private Long webtoonId;
        private Long episodeId;

        private Long memberHasNftId;
        private String nftNames;
        private NftType nftType;
        private String nftImage;

        private Long profileSkinId;
        private String profileSkinName;
        private String profileSkinImage;
        private boolean defaultSkin;
    }
}
