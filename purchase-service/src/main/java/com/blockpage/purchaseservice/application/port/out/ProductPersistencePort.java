package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.application.service.ProductService.ProductEntityDto;
import java.util.List;

public interface ProductPersistencePort {

    List<ProductEntityDto> findAllNft();
    List<ProductEntityDto> findAllProfileSkin();
}
