package com.blockpage.purchaseservice.domain;

import static java.lang.Boolean.*;

import com.blockpage.purchaseservice.adaptor.infrastructure.value.PersistType;
import com.blockpage.purchaseservice.adaptor.infrastructure.value.ProductType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private final long FREE_POLICY_FOR_YEARS = 100l; //100년
    private final long RENTAL_POLICY_FOR_DAYS = 2l;  //2일

    private ProductType productType;

    private PersistType persistType;

    private Boolean profileSkinDefault;

    private LocalDateTime expiredDate;

    public LocalDateTime makeExpiredDate(PersistType persistType) {
        switch (persistType) {
            case RENTAL -> this.expiredDate = LocalDateTime.now().plusDays(RENTAL_POLICY_FOR_DAYS);
            case PERMANENT -> this.expiredDate = LocalDateTime.now().plusYears(FREE_POLICY_FOR_YEARS);
            default -> throw new IllegalStateException("Unexpected value: " + persistType);
        }
        return this.expiredDate;
    }

    public void changeProfileSkinDefault(Purchase purchase) {
        if (purchase.profileSkinDefault == TRUE) {
            purchase.profileSkinDefault = FALSE;
        } else {
            purchase.profileSkinDefault = TRUE;
        }
    }

    public Purchase(ProductType productType, PersistType persistType) {
        this.productType = productType;
        this.persistType = persistType;
    }
}