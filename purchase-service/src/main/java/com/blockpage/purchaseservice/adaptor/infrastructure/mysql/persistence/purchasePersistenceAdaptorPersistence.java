package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.persistence;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasEpisodeBMRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasNftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasProfileSkinRepository;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class purchasePersistenceAdaptorPersistence implements PurchasePersistencePort {

    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;
    private final MemberHasNftRepository memberHasNftRepository;
    private final MemberHasEpisodeBMRepository memberHasEpisodeBMRepository;

    @Override
    public Long saveProfileSkin(PurchaseDto purchaseOutDto) {

        MemberHasProfileSkinEntity memberHasProfileSkinEntity = memberHasProfileSkinRepository.save(
            MemberHasProfileSkinEntity.toEntity(purchaseOutDto));
        return memberHasProfileSkinEntity.getId();
    }

    @Override
    public Long saveEpisodeBM(PurchaseDto purchaseOutDto) {
        MemberHasEpisodeBMEntity memberHasEpisodeBMEntity = memberHasEpisodeBMRepository.save(
            MemberHasEpisodeBMEntity.toEntity(purchaseOutDto));
        return memberHasEpisodeBMEntity.getId();

    }

    @Override
    public Long saveNft(PurchaseDto purchaseOutDto) {
        MemberHasNftEntity memberHasNftEntity = memberHasNftRepository.save(MemberHasNftEntity.toEntity(purchaseOutDto));
        return memberHasNftEntity.getId();
    }
}
