package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
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

    public static MemberHasEpisodeBMEntity toEntity(PurchaseOutPortDto purchaseOutPortDto) {
        return MemberHasEpisodeBMEntity.builder()
            .memberId(purchaseOutPortDto.getMemberId())
            .episodeId(purchaseOutPortDto.getEpisodeId())
            .webtoonId(purchaseOutPortDto.getWebtoonId())
            .persistType(purchaseOutPortDto.getPersistType())
            .expiredDate(purchaseOutPortDto.getExpiredDate())
            .build();
    }
}
