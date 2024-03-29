package com.blockpage.purchaseservice.adaptor.web.view;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import com.blockpage.purchaseservice.application.service.PurchaseService.NftDto;
import com.blockpage.purchaseservice.application.service.PurchaseService.ProfileSkinDto;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseView {

    private String message;

    private String memberId;
    private String leftTimer;
    private String expiredDate;

    private Long memberHasEpisodeBMId;
    private Long episodeId;
    private Integer episodeNumber;
    private String webtoonTitle;
    private Long webtoonId;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private String genre;

    private Long memberHasNftId;
    private NftDetail nftDetail;

    private Long memberHasProfileSkinId;
    private ProfileSkinDetail profileSkinDetail;
    private Boolean profileSkinDefault;

    public PurchaseView(PurchaseDto purchaseDto) {
        this.memberId = purchaseDto.getMemberId();
        this.memberHasEpisodeBMId = purchaseDto.getMemberHasEpisodeBMId();
        this.episodeId = purchaseDto.getEpisodeId();
        this.webtoonId = purchaseDto.getWebtoonId();
        this.webtoonTitle = purchaseDto.getWebtoonTitle();
        this.webtoonThumbnail = purchaseDto.getWebtoonThumbnail();
        this.creator = purchaseDto.getCreator();
        this.illustrator = purchaseDto.getIllustrator();
        this.genre = purchaseDto.getGenre();
        this.memberHasNftId = purchaseDto.getMemberHasNftId();
        this.nftDetail = purchaseDto.getNftDto() != null ? new NftDetail(purchaseDto.getNftDto()) : null;
        this.memberHasProfileSkinId = purchaseDto.getMemberHasProfileSkinId();
        this.profileSkinDefault = purchaseDto.getProfileSkinDefault();
        this.profileSkinDetail = purchaseDto.getProfileSkinDto() != null ? new ProfileSkinDetail(purchaseDto.getProfileSkinDto()) : null;
        this.leftTimer = purchaseDto.getLeftTimer();
        this.episodeNumber = purchaseDto.getEpisodeNumber();
        this.expiredDate = purchaseDto.getExpiredDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));;
    }

    @Getter
    public class NftDetail {

        private Long id;
        private String memberId;
        private String creatorId;
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
        private Integer profileSkinBlockPrice;
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
