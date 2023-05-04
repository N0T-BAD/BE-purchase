package com.blockpage.purchaseservice.adaptor.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "webtoon_bm")
public class WebtoonBMEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long webtoonId;

    @Column
    private Long episodeId;

    @Column
    private Integer webtoonBMBlockPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private WebtoonBMStatus webtoonBMStatus;
}
