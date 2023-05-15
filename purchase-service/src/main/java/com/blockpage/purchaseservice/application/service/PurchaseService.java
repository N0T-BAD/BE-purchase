package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.external.block.request.BlockPayRequestParams;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.domain.Purchase;
import lombok.RequiredArgsConstructor;
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

        BlockPayRequestParams blockPayRequestParams = BlockPayRequestParams.addEssentialParams(query.getBlockQuantity());
        blockServicePort.blockPay(blockPayRequestParams);

        Purchase purchase = Purchase.initPurchaseForSave(query);

        switch (purchase.getProductType()) {
            case NFT -> purchasePersistencePort.saveNft(purchase);
            case EPISODE_BM -> purchasePersistencePort.saveEpisodeBM(purchase);
            case PROFILE_SKIN -> purchasePersistencePort.saveProfileSkin(purchase);
        }
    }
}
