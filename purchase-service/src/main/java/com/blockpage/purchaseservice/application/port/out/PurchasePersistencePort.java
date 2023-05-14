package com.blockpage.purchaseservice.application.port.out;

public interface PurchasePersistencePort {

    Long saveProfileSkin(PurchaseOutDto purchaseOutDto);

    Long saveEpisodeBM(PurchaseOutDto purchaseOutDto);

    Long saveNft(PurchaseOutDto purchaseOutDto);
}
