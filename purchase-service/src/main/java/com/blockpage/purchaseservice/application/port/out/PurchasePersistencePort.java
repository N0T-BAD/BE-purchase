package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public interface PurchasePersistencePort {

    MemberHasProfileSkinEntityDto saveProfileSkin(Purchase purchase);

    MemberHasEpisodeBMEntityDto saveEpisodeBM(Purchase purchase);

    MemberHasNftEntityDto saveNft(Purchase purchase);

    PurchaseDto findNft(Long memberId);

    PurchaseDto findEpisodeBM(Long memberId, Long webtoonId);

    PurchaseDto findProfileSkin(Long memberId);

    @Builder
    @Getter
    class MemberHasProfileSkinEntityDto {

        private Long id;
        private ProfileSkinEntity profileSkinEntity;
        private Long memberId;
        private Boolean defaultSkin;

        public static MemberHasProfileSkinEntityDto toDto(MemberHasProfileSkinEntity entity) {
            return MemberHasProfileSkinEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .profileSkinEntity(entity.getProfileSkinEntity())
                .defaultSkin(entity.getDefaultSkin())
                .build();
        }
    }

    @Builder
    @Getter
    class MemberHasEpisodeBMEntityDto {

        private Long id;
        private Long memberId;
        private Long episodeId;
        private Long webtoonId;
        private PersistType persistType;
        private LocalDateTime expiredDate;

        public static MemberHasEpisodeBMEntityDto toDto(MemberHasEpisodeBMEntity entity) {
            return MemberHasEpisodeBMEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .episodeId(entity.getEpisodeId())
                .webtoonId(entity.getWebtoonId())
                .persistType(entity.getPersistType())
                .expiredDate(entity.getExpiredDate())
                .build();
        }
    }

    @Builder
    @Getter
    class MemberHasNftEntityDto {

        private Long id;
        private NftEntity nftEntity;
        private Long memberId;

        public static MemberHasNftEntityDto toDto(MemberHasNftEntity entity) {
            return MemberHasNftEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .nftEntity(entity.getNftEntity())
                .build();
        }
    }
}
