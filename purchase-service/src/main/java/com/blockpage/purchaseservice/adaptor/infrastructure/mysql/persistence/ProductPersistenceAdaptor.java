package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.persistence;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.NftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.ProfileSkinRepository;
import com.blockpage.purchaseservice.application.port.out.ProductPersistencePort;
import com.blockpage.purchaseservice.domain.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdaptor implements ProductPersistencePort {

    private final ProfileSkinRepository profileSkinRepository;
    private final NftRepository nftRepository;

    @Override
    public List<Product> findAllNft() {
        List<NftEntity> nftEntities = nftRepository.findAll();
        return nftEntities.stream()
            .map(Product::toDomainFromNftEntity)
            .toList();
    }

    @Override
    public List<Product> findAllProfileSkin() {
        List<ProfileSkinEntity> profileSkinEntities = profileSkinRepository.findAll();
        return profileSkinEntities.stream()
            .map(Product::toDomainFromProfileSkinEntity)
            .toList();
    }
}
