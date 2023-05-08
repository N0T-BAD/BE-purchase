package com.blockpage.purchaseservice.application.port.out;

public interface SavePurchasePort {

    Long saveProfileSkin(PurchaseOutPortDto purchaseOutPortDto);

    Long saveEpisodeBM(PurchaseOutPortDto purchaseOutPortDto);

    Long saveNft(PurchaseOutPortDto purchaseOutPortDto);
}
