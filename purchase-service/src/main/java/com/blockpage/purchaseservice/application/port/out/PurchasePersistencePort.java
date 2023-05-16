package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.domain.Purchase;
import java.util.List;

public interface PurchasePersistencePort {

    void saveProfileSkin(Purchase purchase);

    void saveEpisodeBM(Purchase purchase);

    void saveNft(Purchase purchase);

    List<Purchase> findNft(Long memberId);

    List<Purchase> findEpisodeBMByWebtoonId(Long memberId, Long webtoonId);

    List<Purchase> findProfileSkin(Long memberId);

}
