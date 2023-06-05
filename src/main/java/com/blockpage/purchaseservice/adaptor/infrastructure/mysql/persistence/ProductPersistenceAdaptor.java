package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.persistence;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.NftEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.NftRepository;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository.ProfileSkinRepository;
import com.blockpage.purchaseservice.application.port.out.ProductPersistencePort;
import com.blockpage.purchaseservice.application.service.ProductService.ProductEntityDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdaptor implements ProductPersistencePort {

    private final ProfileSkinRepository profileSkinRepository;
    private final NftRepository nftRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntityDto> findAllNft() {
        List<NftEntity> nftEntities = nftRepository.findAll();
        return nftEntities.stream()
            .map(ProductEntityDto::toDtoFromNftEntity)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntityDto> findAllProfileSkin() {
        List<ProfileSkinEntity> profileSkinEntities = profileSkinRepository.findAll();
        return profileSkinEntities.stream()
            .map(ProductEntityDto::toDtoFromProfileSkinEntity)
            .toList();
    }
}
