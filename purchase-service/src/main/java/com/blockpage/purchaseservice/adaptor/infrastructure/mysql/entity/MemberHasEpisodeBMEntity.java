package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;

import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
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
    private Long memberId;

    @Column
    private Long episodeId;

    @Column
    private Long webtoonId;

    @Enumerated(EnumType.STRING)
    private PersistType persistType;

    @Column
    private LocalDateTime expiredDate;

    public static MemberHasEpisodeBMEntity toEntity(PurchaseDto purchaseOutDto) {
        return MemberHasEpisodeBMEntity.builder()
            .memberId(purchaseOutDto.getMemberId())
            .episodeId(purchaseOutDto.getEpisodeId())
            .webtoonId(purchaseOutDto.getWebtoonId())
            .persistType(purchaseOutDto.getPersistType())
            .expiredDate(purchaseOutDto.getExpiredDate())
            .build();
    }
}
