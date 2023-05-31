package com.blockpage.purchaseservice.application.service;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.block.requestbody.BlockPayRequestParams;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestBody;
import com.blockpage.purchaseservice.adaptor.infrastructure.message.sync.member.requestbody.ChangeProfileSkinRequestParams;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.ProductType;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.out.BlockServicePort;
import com.blockpage.purchaseservice.application.port.out.MemberServicePort;
import com.blockpage.purchaseservice.application.port.out.PurchasePersistencePort;
import com.blockpage.purchaseservice.domain.Purchase;
import com.blockpage.purchaseservice.domain.Purchase.NftWrapper;
import com.blockpage.purchaseservice.domain.Purchase.ProfileSkinWrapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseService implements PurchaseUseCase {

    private final PurchasePersistencePort purchasePersistencePort;
    private final BlockServicePort blockServicePort;
    private final MemberServicePort memberServicePort;

    @Override
    @Transactional
    public void purchaseProduct(PurchaseQuery query) {

        Purchase purchase = Purchase.initPurchaseForSave(query);
        String memberId = purchase.getMemberId();

        switch (purchase.getProductType()) {
            case NFT -> {
                purchasePersistencePort.saveNft(purchase);
                BlockPayRequestParams params = BlockPayRequestParams.addEssentialParams(query);
                blockServicePort.blockPay(memberId, params);
            }
            case EPISODE_BM_PAID -> {
                purchasePersistencePort.saveEpisodeBM(purchase);
                BlockPayRequestParams params = BlockPayRequestParams.addEssentialParams(query);
                blockServicePort.blockPay(memberId, params);
            }
            case EPISODE_BM_FREE -> {
                purchasePersistencePort.saveEpisodeBM(purchase);
            }
            case PROFILE_SKIN -> {
                purchasePersistencePort.saveProfileSkin(purchase);
                BlockPayRequestParams params = BlockPayRequestParams.addEssentialParams(query);
                blockServicePort.blockPay(memberId, params);
            }
            default -> throw new IllegalStateException("Unexpected value: " + purchase.getProductType());
        }
    }

    @Override
    @Transactional
    public void changeProfileSkinPurchases(ChangePurchaseQuery query) {
        Purchase purchase = purchasePersistencePort.changeProfileSkin(query.getMemberId(), query.getMemberProfileSkinId());

        ChangeProfileSkinRequestBody body = ChangeProfileSkinRequestBody.addEssentialBody(purchase);
        ChangeProfileSkinRequestParams params = ChangeProfileSkinRequestParams.addEssentialParams("profileSkin");
        memberServicePort.changeProfileSkin(query, params, body);

    }

    @Override
    public List<PurchaseDto> purchaseQuery(FindPurchaseQuery findPurchaseQuery) {
        List<Purchase> purchaseList;
        switch (ProductType.findByValue(findPurchaseQuery.getProductType())) {
            case NFT -> purchaseList = purchasePersistencePort.findNft(findPurchaseQuery.getMemberId());
            case EPISODE_BM_PAID -> purchaseList = purchasePersistencePort.findEpisodeBMByWebtoonIdAndFree(findPurchaseQuery.getMemberId(),
                findPurchaseQuery.getWebtoonId(), Boolean.FALSE);
            case EPISODE_BM_FREE -> purchaseList = purchasePersistencePort.findEpisodeBMByWebtoonIdAndFree(findPurchaseQuery.getMemberId(),
                findPurchaseQuery.getWebtoonId(), Boolean.TRUE);
            case PROFILE_SKIN -> purchaseList = purchasePersistencePort.findProfileSkinByMemberId(findPurchaseQuery.getMemberId());
            default -> throw new IllegalStateException("Unexpected value: " + ProductType.findByValue(findPurchaseQuery.getProductType()));
        }
        return purchaseList.stream()
            .map(PurchaseDto::toDto)
            .collect(Collectors.toList());
    }

    @Getter
    @Builder
    public static class PurchaseDto {

        private String memberId;
        private LocalDateTime expiredDate;
        private Integer blockQuantity;

        private Long memberHasEpisodeBMId;
        private Long episodeId;
        private Long webtoonId;

        private Long memberHasNftId;
        private NftDto nftDto;

        private Long memberHasProfileSkinId;
        private ProfileSkinDto profileSkinDto;
        private Boolean profileSkinDefault;

        public static PurchaseDto toDto(Purchase purchase) {
            return PurchaseDto.builder()
                .memberId(purchase.getMemberId())
                .expiredDate(purchase.getExpiredDate())
                .blockQuantity(purchase.getBlockQuantity())
                .memberHasEpisodeBMId(purchase.getMemberHasEpisodeBMId())
                .episodeId(purchase.getEpisodeId())
                .webtoonId(purchase.getWebtoonId())
                .memberHasNftId(purchase.getMemberHasNftId())
                .nftDto(purchase.getNftWrapper() != null ? NftDto.initForGet(purchase.getNftWrapper()) : null)
                .memberHasProfileSkinId(purchase.getMemberHasProfileSkinId())
                .profileSkinDto(
                    purchase.getProfileSkinWrapper() != null ? ProfileSkinDto.initForGet(purchase.getProfileSkinWrapper()) : null)
                .profileSkinDefault(purchase.getProfileSkinDefault())
                .build();
        }
    }

    @Builder
    @Getter
    public static class NftDto {

        private Long id;
        private String memberId;
        private String creatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public static NftDto initForGet(NftWrapper nftWrapper) {
            return NftDto.builder()
                .id(nftWrapper.getId())
                .memberId(nftWrapper.getMemberId())
                .creatorId(nftWrapper.getCreatorId())
                .nftName(nftWrapper.getNftName())
                .nftBlockPrice(nftWrapper.getNftBlockPrice())
                .nftImage(nftWrapper.getNftImage())
                .nftType(nftWrapper.getNftType())
                .build();
        }
    }

    @Getter
    @Builder
    public static class ProfileSkinDto {

        private Long id;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public static ProfileSkinDto initForGet(ProfileSkinWrapper wrapper) {
            return ProfileSkinDto.builder()
                .id(wrapper.getId())
                .profileSkinName(wrapper.getProfileSkinName())
                .profileSkinDescription(wrapper.getProfileSkinDescription())
                .profileSkinBlockPrice(wrapper.getProfileSkinBlockPrice())
                .profileSkinImage(wrapper.getProfileSkinImage())
                .build();
        }
    }
}
