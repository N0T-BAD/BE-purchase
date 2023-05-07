package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.application.port.out.PurchaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
public class MemberHasEpisodeBMEntity {

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

    public static MemberHasEpisodeBMEntity toEntity(PurchaseDto purchaseDto) {
        return MemberHasEpisodeBMEntity.builder()
            .memberId(purchaseDto.getMemberId())
            .episodeId(purchaseDto.getEpisodeId())
            .webtoonId(purchaseDto.getWebtoonId())
            .persistType(purchaseDto.getPersistType())
            .expiredDate(purchaseDto.getExpiredDate())
            .build();
    }
}
