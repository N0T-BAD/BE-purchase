package com.blockpage.purchaseservice.adaptor.infrastructure.entity;

import com.blockpage.purchaseservice.application.port.out.PurchaseDto;
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

@Builder
@Getter
@Entity
@Table(name = "member_has_profile_skin")
@AllArgsConstructor
@NoArgsConstructor
public class MemberHasProfileSkinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_skin_id")
    private ProfileSkinEntity profileSkinEntity;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "default_skin")
    private Boolean defaultSkin;

    public static MemberHasProfileSkinEntity toEntity(PurchaseDto purchaseDto) {
        return MemberHasProfileSkinEntity.builder()
            .profileSkinEntity(new ProfileSkinEntity()) //상품 Entity 넣어줘야함..!
            .memberId(purchaseDto.getMemberId())
            .defaultSkin(Boolean.FALSE)
            .build();
    }
}
