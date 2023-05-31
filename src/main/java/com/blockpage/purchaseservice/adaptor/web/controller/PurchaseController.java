package com.blockpage.purchaseservice.adaptor.web.controller;


import static com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.*;

import com.blockpage.purchaseservice.adaptor.web.view.ApiWrapperResponse;

import com.blockpage.purchaseservice.adaptor.web.requestbody.PurchaseRequest;
import com.blockpage.purchaseservice.adaptor.web.view.PurchaseView;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase;
import com.blockpage.purchaseservice.application.port.in.PurchaseUseCase.PurchaseQuery;
import com.blockpage.purchaseservice.application.service.PurchaseService.PurchaseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase-service/v1/purchases")
public class PurchaseController {

    private final PurchaseUseCase purchaseUseCase;

    @GetMapping
    public ResponseEntity<ApiWrapperResponse<List<PurchaseView>>> getPurchases(
        @RequestParam String type,
        @RequestHeader String memberId,
        @RequestParam(required = false) Long webtoonId) {

        List<PurchaseDto> purchaseDtoList = purchaseUseCase.purchaseQuery(FindPurchaseQuery.toQuery(memberId, type, webtoonId));
        List<PurchaseView> purchaseViews = purchaseDtoList.stream()
            .map(PurchaseView::new)
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse<>(purchaseViews));
    }

    @PostMapping
    public ResponseEntity<ApiWrapperResponse<PurchaseView>> postPurchases(
        @RequestHeader String memberId,
        @RequestParam String type,
        @RequestParam(required = false) Long webtoonId,
        @RequestBody PurchaseRequest purchaseRequest) {

        purchaseUseCase.purchaseProduct(PurchaseQuery.toQuery(memberId, type, webtoonId, purchaseRequest));

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(new PurchaseView("구매내역이 정상적으로 생성되었습니다.")));
    }

    @PutMapping
    public ResponseEntity<ApiWrapperResponse> patchPurchasesProfileSkinDefault(
        @RequestHeader String memberId,
        @RequestBody PurchaseRequest purchaseRequest
    ) {
        purchaseUseCase.changeProfileSkinPurchases(ChangePurchaseQuery.toQuery(memberId, purchaseRequest));
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiWrapperResponse(new PurchaseView("구매내역이 정상적으로 변경되었습니다.")));
    }
}
