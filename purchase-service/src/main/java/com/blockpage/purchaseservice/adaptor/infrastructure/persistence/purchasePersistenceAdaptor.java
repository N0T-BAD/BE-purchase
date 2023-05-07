package com.blockpage.purchaseservice.adaptor.infrastructure.persistence;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.MemberHasEpisodeBMRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.MemberHasNftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.repository.MemberHasProfileSkinRepository;
import com.blockpage.purchaseservice.application.port.out.SavePurchasePort;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class purchasePersistenceAdaptor implements SavePurchasePort {

    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;
    private final MemberHasNftRepository memberHasNftRepository;
    private final MemberHasEpisodeBMRepository memberHasEpisodeBMRepository;

    @Override
    public Long saveProfileSkin(PurchaseOutPortDto purchaseOutPortDto) {

        MemberHasProfileSkinEntity memberHasProfileSkinEntity = memberHasProfileSkinRepository.save(
            MemberHasProfileSkinEntity.toEntity(purchaseOutPortDto));
        return memberHasProfileSkinEntity.getId();
    }

    @Override
    public Long saveEpisodeBM(PurchaseOutPortDto purchaseOutPortDto) {
        MemberHasEpisodeBMEntity memberHasEpisodeBMEntity = memberHasEpisodeBMRepository.save(
            MemberHasEpisodeBMEntity.toEntity(purchaseOutPortDto));
        return memberHasEpisodeBMEntity.getId();

    }

    @Override
    public Long saveNft(PurchaseOutPortDto purchaseOutPortDto) {
        MemberHasNftEntity memberHasNftEntity = memberHasNftRepository.save(MemberHasNftEntity.toEntity(purchaseOutPortDto));
        return memberHasNftEntity.getId();
    }
}
