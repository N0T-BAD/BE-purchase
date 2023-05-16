package com.blockpage.purchaseservice.adaptor.web.controller;

import com.blockpage.purchaseservice.adaptor.web.view.ApiWrapperResponse;
import com.blockpage.purchaseservice.adaptor.web.view.ProductsView;
import java.util.ArrayList;
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


    /**
     * 모든 NFT 상품 조회 API              | /v1/products?type=nft
     * 모든 프로필 스킨 상품 조회 API        | /v1/products?type=profile-skin
     */
    @GetMapping
    public ResponseEntity<ApiWrapperResponse<ProductsView>> getAllProducts(@RequestParam String type) {

        List<ProductsView> productsView = new ArrayList<>();



        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(productsView));
    }
}
