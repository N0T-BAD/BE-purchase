package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.application.service.PurchaseService.NftDto;
import com.blockpage.purchaseservice.application.service.PurchaseService.ProfileSkinDto;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseView {

    private String message;

    private Long memberId;
    private LocalDateTime expiredDate;

    private Long memberHasEpisodeBMId;
    private Long episodeId;
    private Long webtoonId;

    private Long memberHasNftId;
    private NftDetail nftDetail;

    private Long memberHasProfileSkinId;
    private ProfileSkinDetail profileSkinDetail;
    private Boolean profileSkinDefault;

    public PurchaseView(PurchaseDto purchaseDto) {
        this.memberId = purchaseDto.getMemberId();
        this.expiredDate = purchaseDto.getExpiredDate();
        this.memberHasEpisodeBMId = purchaseDto.getMemberHasEpisodeBMId();
        this.episodeId = purchaseDto.getEpisodeId();
        this.webtoonId = purchaseDto.getWebtoonId();
        this.memberHasNftId = purchaseDto.getMemberHasNftId();
        this.nftDetail = purchaseDto.getNftDto() != null ? new NftDetail(purchaseDto.getNftDto()) : null;
        this.memberHasProfileSkinId = purchaseDto.getMemberHasProfileSkinId();
        this.profileSkinDefault = purchaseDto.getProfileSkinDefault();
        this.profileSkinDetail = purchaseDto.getProfileSkinDto() != null ? new ProfileSkinDetail(purchaseDto.getProfileSkinDto()) : null;
    }

    @Getter
    public class NftDetail {

        private Long id;
        private Long memberId;
        private Long creatorId;
        private String nftName;
        private String nftDescription;
        private Integer nftBlockPrice;
        private String nftImage;
        private NftType nftType;

        public NftDetail(NftDto dto) {
            this.id = dto.getId();
            this.memberId = dto.getMemberId();
            this.creatorId = dto.getCreatorId();
            this.nftName = dto.getNftName();
            this.nftDescription = dto.getNftDescription();
            this.nftBlockPrice = dto.getNftBlockPrice();
            this.nftImage = dto.getNftImage();
            this.nftType = dto.getNftType();
        }
    }

    @Getter
    public class ProfileSkinDetail {

        private Long id;
        private String profileSkinName;
        private String profileSkinDescription;
        private String profileSkinBlockPrice;
        private String profileSkinImage;

        public ProfileSkinDetail(ProfileSkinDto dto) {
            this.id = dto.getId();
            this.profileSkinName = dto.getProfileSkinName();
            this.profileSkinDescription = dto.getProfileSkinDescription();
            this.profileSkinBlockPrice = dto.getProfileSkinBlockPrice();
            this.profileSkinImage = dto.getProfileSkinImage();
        }
    }

    public PurchaseView(String message) {
        this.message = message;
    }
}
