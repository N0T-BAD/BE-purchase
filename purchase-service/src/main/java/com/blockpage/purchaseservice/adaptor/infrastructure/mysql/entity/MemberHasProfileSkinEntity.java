package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
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

@Builder
@Getter
@Entity
@Table(name = "member_has_profile_skin")
@AllArgsConstructor
@NoArgsConstructor
public class MemberHasProfileSkinEntity extends BaseEntity {

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

    public static MemberHasProfileSkinEntity toEntity(PurchaseDto purchaseOutDto) {
        return MemberHasProfileSkinEntity.builder()
            .profileSkinEntity(new ProfileSkinEntity()) //상품 Entity 넣어줘야함..!
            .memberId(purchaseOutDto.getMemberId())
            .defaultSkin(Boolean.FALSE)
            .build();
    }
}
