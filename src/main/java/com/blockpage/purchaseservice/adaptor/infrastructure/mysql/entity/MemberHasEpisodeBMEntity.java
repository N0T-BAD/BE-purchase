package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;

import com.blockpage.purchaseservice.domain.Purchase;
import com.blockpage.purchaseservice.domain.Purchase.ProductType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "member_has_episode_bm")
public class MemberHasEpisodeBMEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @Column
    private Long episodeId;

    @Column
    private Long webtoonId;

    @Column
    private String webtoonTitle;

    @Column
    private Integer episodeNumber;

    @Column(name = "block_quantity")
    private Integer blockQuantity;

    @Column
    @Enumerated(EnumType.STRING)
    private PersistType persistType;

    @Column
    private Boolean free;

    @Column
    private LocalDateTime expiredDate;

    @Column
    private String webtoonThumbnail;

    @Column
    private String creator;

    @Column
    private String illustrator;

    @Column
    private String genre;

    public static MemberHasEpisodeBMEntity toEntity(Purchase purchase) {
        return MemberHasEpisodeBMEntity.builder()
            .id(purchase.getMemberHasEpisodeBMId())
            .memberId(purchase.getMemberId())
            .episodeId(purchase.getEpisodeId())
            .episodeNumber(purchase.getEpisodeNumber())
            .blockQuantity(purchase.getBlockQuantity())
            .webtoonId(purchase.getWebtoonId())
            .webtoonTitle(purchase.getWebtoonTitle())
            .free(ProductType.findByValue(purchase.getProductType().getValue()) == ProductType.EPISODE_BM_FREE)
            .persistType(PersistType.findByValue(purchase.getPersistType().getValue()))
            .expiredDate(purchase.getExpiredDate())
            .webtoonThumbnail(purchase.getWebtoonThumbnail())
            .creator(purchase.getCreator())
            .illustrator(purchase.getIllustrator())
            .genre(purchase.getGenre())
            .build();
    }
}
