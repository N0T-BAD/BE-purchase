package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.persistence;

import static com.blockpage.purchaseservice.exception.ErrorCode.*;

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
import com.blockpage.purchaseservice.exception.BusinessException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchasePersistenceAdaptor implements PurchasePersistencePort {

    private final MemberHasProfileSkinRepository memberHasProfileSkinRepository;
    private final MemberHasNftRepository memberHasNftRepository;
    private final MemberHasEpisodeBMRepository memberHasEpisodeBMRepository;
    private final ProfileSkinRepository profileSkinRepository;
    private final NftRepository nftRepository;

    @Override
    public void saveProfileSkin(Purchase purchase) {
        ProfileSkinEntity profileSkinEntity = profileSkinRepository.findById(purchase.getProfileSkinWrapper().getId())
            .orElseThrow(
                () -> new BusinessException(NO_FOUND_PROFILE_SKIN_PRODUCT.getMessage(), NO_FOUND_PROFILE_SKIN_PRODUCT.getHttpStatus()));
        memberHasProfileSkinRepository.save(MemberHasProfileSkinEntity.toEntity(purchase, profileSkinEntity));
    }

    @Override
    public void saveEpisodeBM(Purchase purchase) {
        memberHasEpisodeBMRepository.save(MemberHasEpisodeBMEntity.toEntity(purchase));

    }

    @Override
    public void saveNft(Purchase purchase) {
        NftEntity nftEntity = nftRepository.findById(purchase.getNftWrapper().getId())
            .orElseThrow(
                () -> new BusinessException(NO_FOUND_PROFILE_SKIN_PRODUCT.getMessage(), NO_FOUND_PROFILE_SKIN_PRODUCT.getHttpStatus()));
        nftEntity.setMemberId(purchase.getMemberId());
        memberHasNftRepository.save(MemberHasNftEntity.toEntity(purchase, nftEntity));
    }

    @Override
    public List<Purchase> findNft(String memberId) {
        List<MemberHasNftEntity> memberNftEntityList = memberHasNftRepository.findByMemberId(memberId);
        return memberNftEntityList.stream()
            .map(Purchase::toDomainFromMemberNftEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> findEpisodeBMByWebtoonIdAndFree(String memberId, Long webtoonId, Boolean free) {
        List<MemberHasEpisodeBMEntity> memberEpisodeBMEntityList = memberHasEpisodeBMRepository.findByMemberIdAndWebtoonIdAndFree(memberId,
            webtoonId, free);
        return memberEpisodeBMEntityList.stream()
            .map(Purchase::toDomainFromMemberEpisodeBMEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> findProfileSkinByMemberId(String memberId) {
        List<MemberHasProfileSkinEntity> memberProfileSkinEntityList = memberHasProfileSkinRepository.findByMemberId(memberId);
        return memberProfileSkinEntityList.stream()
            .map(Purchase::toDomainFromMemberProfileSkinEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Purchase changeProfileSkin(String memberId, Long memberProfileSkinId) {
        List<MemberHasProfileSkinEntity> entityList = memberHasProfileSkinRepository.findByMemberId(memberId);
        MemberHasProfileSkinEntity oldDefault = entityList.stream()
            .filter(MemberHasProfileSkinEntity::getDefaultSkin)
            .findFirst()
            .orElseThrow(
                () -> new BusinessException(PROFILE_SKIN_INTERNAL_ERROR.getMessage(), PROFILE_SKIN_INTERNAL_ERROR.getHttpStatus())
            );
        MemberHasProfileSkinEntity newDefault = entityList.stream()
            .filter(e -> e.getId().equals(memberProfileSkinId))
            .findFirst()
            .orElseThrow(
                () -> new BusinessException(PROFILE_SKIN_INTERNAL_ERROR.getMessage(), PROFILE_SKIN_INTERNAL_ERROR.getHttpStatus())
            );
        oldDefault.changeDefaultSkin();
        newDefault.changeDefaultSkin();
        return Purchase.toDomainFromMemberProfileSkinEntity(newDefault);
    }
}
