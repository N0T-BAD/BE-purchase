package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import com.blockpage.purchaseservice.application.port.out.PurchaseOutPortDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class MemberHasNftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nft_id")
    private NftEntity nftEntity;

    @Column(name = "member_id")
    private Long memberId;

    public static MemberHasNftEntity toEntity(PurchaseOutPortDto purchaseOutPortDto) {
        return MemberHasNftEntity.builder()
            .memberId(purchaseOutPortDto.getMemberId())
            .nftEntity(new NftEntity()) //상품 Entity 넣어줘야함..!
            .build();
    }
}
