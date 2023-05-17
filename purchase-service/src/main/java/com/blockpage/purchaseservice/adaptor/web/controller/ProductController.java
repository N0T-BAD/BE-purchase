package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.web.view.ApiWrapperResponse;
import com.blockpage.purchaseservice.adaptor.web.view.ProductView;
import com.blockpage.purchaseservice.application.port.in.ProductUseCase;
import com.blockpage.purchaseservice.application.port.in.ProductUseCase.ProductQuery;
import com.blockpage.purchaseservice.application.service.ProductService.ProductEntityDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<ApiWrapperResponse<List<ProductView>>> getAllProducts(@RequestParam String type) {

        List<ProductEntityDto> productEntityDtoList = productUseCase.productQuery(ProductQuery.toQuery(type));
        List<ProductView> productViews = productEntityDtoList.stream()
            .map(ProductView::new)
            .toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(productViews));
    }
}
