package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public interface PurchasePersistencePort {

    MemberHasProfileSkinEntityDto saveProfileSkin(Purchase purchase);

    MemberHasEpisodeBMEntityDto saveEpisodeBM(Purchase purchase);

    MemberHasNftEntityDto saveNft(Purchase purchase);

    List<MemberHasNftEntityDto> findNft(Long memberId);

    List<MemberHasEpisodeBMEntityDto> findEpisodeBMByWebtoonId(Long memberId, Long webtoonId);

    List<MemberHasProfileSkinEntityDto> findProfileSkin(Long memberId);


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
    class MemberHasProfileSkinEntityDto {

        private Long id;
        private ProfileSkinEntityDto profileSkinEntityDto;
        private Long memberId;
        private Boolean defaultSkin;

        public static MemberHasProfileSkinEntityDto toDto(MemberHasProfileSkinEntity entity) {
            return MemberHasProfileSkinEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .profileSkinEntityDto(ProfileSkinEntityDto.toDto(entity.getProfileSkinEntity()))
                .defaultSkin(entity.getDefaultSkin())
                .build();
        }
    }


    @Builder
    @Getter
    class MemberHasNftEntityDto {

        private Long id;
        private NftEntityDto nftEntityDto;
        private Long memberId;

        public static MemberHasNftEntityDto toDto(MemberHasNftEntity entity) {
            return MemberHasNftEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .nftEntityDto(NftEntityDto.toDto(entity.getNftEntity()))
                .build();
        }
    }

    @Builder
    @Getter
    class ProfileSkinEntityDto {

        private Long id;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public static ProfileSkinEntityDto toDto(ProfileSkinEntity entity) {
            return ProfileSkinEntityDto.builder()
                .id(entity.getId())
                .profileSkinName(entity.getProfileSkinName())
                .profileSkinDescription(entity.getProfileSkinDescription())
                .profileSkinBlockPrice(entity.getProfileSkinBlockPrice())
                .profileSkinImage(entity.getProfileSkinImage())
                .build();
        }
    }

    @Builder
    @Getter
    class NftEntityDto {

        private Long id;
        private Long memberId;
        private Long creatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public static NftEntityDto toDto(NftEntity entity) {
            return NftEntityDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .creatorId(entity.getCreatorId())
                .nftName(entity.getNftName())
                .nftBlockPrice(entity.getNftBlockPrice())
                .nftImage(entity.getNftImage())
                .nftType(entity.getNftType())
                .build();
        }
    }
}
