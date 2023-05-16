package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.value.PersistType;
import com.blockpage.purchaseservice.domain.Purchase;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "block_quantity")
    private Integer blockQuantity;

    @Column(name = "default_skin")
    private Boolean defaultSkin;

    @Enumerated(EnumType.STRING)
    private PersistType persistType;

    @Column
    private LocalDateTime expiredDate;

    public static MemberHasProfileSkinEntity toEntity(Purchase purchase, ProfileSkinEntity profileSkinEntity) {
        return MemberHasProfileSkinEntity.builder()
            .id(purchase.getMemberHasProfileSkinId())
            .profileSkinEntity(profileSkinEntity) //상품 Entity 넣어줘야함..!
            .memberId(purchase.getMemberId())
            .blockQuantity(purchase.getBlockQuantity())
            .defaultSkin(purchase.getProfileSkinDefault())
            .persistType(PersistType.findByValue(purchase.getPersistType().getValue()))
            .expiredDate(purchase.getExpiredDate())
            .build();
    }
}
