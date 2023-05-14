package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.NftType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
