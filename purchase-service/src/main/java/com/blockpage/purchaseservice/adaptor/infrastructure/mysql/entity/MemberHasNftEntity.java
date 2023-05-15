package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.domain.Purchase;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "member_has_nft")
public class MemberHasNftEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nft_id")
    private NftEntity nftEntity;

    @Column(name = "member_id")
    private Long memberId;

    public static MemberHasNftEntity toEntity(Purchase purchase, NftEntity nftEntity) {
        return MemberHasNftEntity.builder()
            .id(purchase.getMemberHasNftId())
            .memberId(purchase.getMemberId())
            .nftEntity(nftEntity)
            .build();
    }
}
