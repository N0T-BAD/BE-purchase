package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
