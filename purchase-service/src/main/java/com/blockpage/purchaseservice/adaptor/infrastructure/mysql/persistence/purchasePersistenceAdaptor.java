package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.persistence;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasEpisodeBMRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasNftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.MemberHasProfileSkinRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.NftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.ProfileSkinRepository;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.domain.Purchase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class purchasePersistenceAdaptor implements PurchasePersistencePort {

    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;
    private final MemberHasNftRepository memberHasNftRepository;
    private final MemberHasEpisodeBMRepository memberHasEpisodeBMRepository;
    private final ProfileSkinRepository profileSkinRepository;
    private final NftRepository nftRepository;

    @Override
    public void saveProfileSkin(Purchase purchase) {
        ProfileSkinEntity profileSkinEntity = profileSkinRepository.findById(purchase.getProfileSkinWrapper().getId()).get();
        memberHasProfileSkinRepository.save(MemberHasProfileSkinEntity.toEntity(purchase, profileSkinEntity));
    }

    @Override
    public void saveEpisodeBM(Purchase purchase) {
        memberHasEpisodeBMRepository.save(MemberHasEpisodeBMEntity.toEntity(purchase));

    }

    @Override
    public void saveNft(Purchase purchase) {
        NftEntity nftEntity = nftRepository.findById(purchase.getNftWrapper().getId()).get();
        memberHasNftRepository.save(MemberHasNftEntity.toEntity(purchase, nftEntity));
    }

    @Override
    public List<Purchase> findNft(Long memberId) {
        List<MemberHasNftEntity> memberNftEntityList = memberHasNftRepository.findByMemberId(memberId);
        return memberNftEntityList.stream()
            .map(Purchase::toDomainFromMemberNftEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> findEpisodeBMByWebtoonId(Long memberId, Long webtoonId) {
        List<MemberHasEpisodeBMEntity> memberEpisodeBMEntityList = memberHasEpisodeBMRepository.findByMemberIdAndWebtoonId(memberId, webtoonId);
        return memberEpisodeBMEntityList.stream()
            .map(Purchase::toDomainFromMemberEpisodeBMEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> findProfileSkin(Long memberId) {
        List<MemberHasProfileSkinEntity> memberProfileSkinEntityList = memberHasProfileSkinRepository.findByMemberId(memberId);
        return memberProfileSkinEntityList.stream()
            .map(Purchase::toDomainFromMemberProfileSkinEntity)
            .collect(Collectors.toList());
    }
}
