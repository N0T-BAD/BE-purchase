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
public class purchasePersistenceAdaptorPersistence implements PurchasePersistencePort {

    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;
    private final MemberHasNftRepository memberHasNftRepository;
    private final MemberHasEpisodeBMRepository memberHasEpisodeBMRepository;
    private final ProfileSkinRepository profileSkinRepository;
    private final NftRepository nftRepository;

    @Override
    public MemberHasProfileSkinEntityDto saveProfileSkin(Purchase purchase) {
        ProfileSkinEntity profileSkinEntity = profileSkinRepository.findById(purchase.getProfileSkinFk().getId()).get();
        MemberHasProfileSkinEntity memberHasProfileSkinEntity = memberHasProfileSkinRepository.save(
            MemberHasProfileSkinEntity.toEntity(purchase, profileSkinEntity));
        return MemberHasProfileSkinEntityDto.toDto(memberHasProfileSkinEntity);
    }

    @Override
    public MemberHasEpisodeBMEntityDto saveEpisodeBM(Purchase purchase) {
        MemberHasEpisodeBMEntity memberHasEpisodeBMEntity = memberHasEpisodeBMRepository.save(MemberHasEpisodeBMEntity.toEntity(purchase));
        return MemberHasEpisodeBMEntityDto.toDto(memberHasEpisodeBMEntity);

    }

    @Override
    public MemberHasNftEntityDto saveNft(Purchase purchase) {
        NftEntity nftEntity = nftRepository.findById(purchase.getNftFk().getId()).get();
        MemberHasNftEntity memberHasNftEntity = memberHasNftRepository.save(MemberHasNftEntity.toEntity(purchase, nftEntity));
        return MemberHasNftEntityDto.toDto(memberHasNftEntity);
    }

    @Override
    public List<MemberHasNftEntityDto> findNft(Long memberId) {
        List<MemberHasNftEntity> memberNftEntityList = memberHasNftRepository.findByMemberId(memberId);
        return memberNftEntityList.stream()
            .map(MemberHasNftEntityDto::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<MemberHasEpisodeBMEntityDto> findEpisodeBMByWebtoonId(Long memberId, Long webtoonId) {
        List<MemberHasEpisodeBMEntity> memberEpisodeBMEntityList = memberHasEpisodeBMRepository.findByMemberIdAndWebtoonIdAnd(memberId,
            webtoonId);
        return memberEpisodeBMEntityList.stream()
            .map(MemberHasEpisodeBMEntityDto::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<MemberHasProfileSkinEntityDto> findProfileSkin(Long memberId) {
        List<MemberHasProfileSkinEntity> memberProfileSkinEntityList = memberHasProfileSkinRepository.findByMemberId(memberId);
        return memberProfileSkinEntityList.stream()
            .map(MemberHasProfileSkinEntityDto::toDto)
            .collect(Collectors.toList());
    }
}
