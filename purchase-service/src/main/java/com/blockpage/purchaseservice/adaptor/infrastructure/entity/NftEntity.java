package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.NftType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "nft")
public class NftEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long creatorId;

    @Column
    private String nftName;

    @Column
    private String nftDescription;

    @Column
    private Integer nftBlockPrice;

    @Column
    private Integer nftStock;

    @Column
    private String nftImage;

    @Enumerated(EnumType.STRING)
    private NftType nftType;

}
