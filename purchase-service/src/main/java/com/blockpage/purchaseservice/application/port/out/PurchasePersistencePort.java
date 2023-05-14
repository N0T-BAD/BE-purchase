package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;

public interface PurchasePersistencePort {

    Long saveProfileSkin(PurchaseDto purchaseOutDto);

    Long saveEpisodeBM(PurchaseDto purchaseOutDto);

    Long saveNft(PurchaseDto purchaseOutDto);
}
