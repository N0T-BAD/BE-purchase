package com.blockpage.purchaseservice.adaptor.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_has_webtoon_bm")
public class MemberHasWebtoonBMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "episode_id")
    private Long episode_id;

    @Enumerated(EnumType.STRING)
    private PersistType persistType;

    @Column
    private LocalDateTime expiredDate;
}
