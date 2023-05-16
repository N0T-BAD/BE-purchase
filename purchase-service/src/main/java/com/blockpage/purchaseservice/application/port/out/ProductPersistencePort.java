package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.domain.Product;
import java.util.List;

public interface ProductPersistencePort {

    List<Product> findAllNft();
    List<Product> findAllProfileSkin();
}
