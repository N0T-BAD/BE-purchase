package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;

import com.blockpage.purchaseservice.domain.Purchase;
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

    public static MemberHasEpisodeBMEntity toEntity(Purchase purchase) {
        return MemberHasEpisodeBMEntity.builder()
            .id(purchase.getMemberHasEpisodeBMId())
            .memberId(purchase.getMemberId())
            .episodeId(purchase.getEpisodeId())
            .webtoonId(purchase.getWebtoonId())
            .persistType(PersistType.findByValue(purchase.getPersistType().getValue()))
            .expiredDate(purchase.getExpiredDate())
            .build();
    }
}
