package com.blockpage.purchaseservice.application.port.in;

import com.blockpage.purchaseservice.application.service.ProductService.ProductEntityDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public interface ProductUseCase {

    List<ProductEntityDto> productQuery(ProductQuery productQuery);

    @Getter
    @Builder
    class ProductQuery {

        private String type;

        public static ProductQuery toQuery(String type) {
            return ProductQuery.builder()
                .type(type)
                .build();
        }
    }
}