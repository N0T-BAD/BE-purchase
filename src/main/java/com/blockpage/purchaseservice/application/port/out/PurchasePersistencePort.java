package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.domain.Purchase;
import java.util.List;

public interface PurchasePersistencePort {

    void saveProfileSkin(Purchase purchase);

    void saveEpisodeBM(Purchase purchase);

    void saveNft(Purchase purchase);

    List<Purchase> findNft(String memberId);

    List<Purchase> findEpisodeBMByWebtoonId(String memberId, Long webtoonId, Boolean free);

    List<Purchase> findProfileSkinByMemberId(String memberId);

    Purchase changeProfileSkin(String memberId, Long memberProfileSkinId);
}
