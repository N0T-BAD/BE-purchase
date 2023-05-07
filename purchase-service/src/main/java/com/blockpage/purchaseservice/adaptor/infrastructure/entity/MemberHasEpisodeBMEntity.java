package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

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
}
