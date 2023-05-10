package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

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
@Table(name = "episode_bm")
public class EpisodeBMEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long episodeId;

    @Column
    private Long webtoonId;

    @Column
    private Integer episodeBMBlockPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.episodeBMStatus episodeBMStatus;
}