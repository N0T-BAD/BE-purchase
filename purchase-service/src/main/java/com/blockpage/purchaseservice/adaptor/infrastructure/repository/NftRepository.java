package com.blockpage.purchaseservice.adaptor.infrastructure.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.NftEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<NftEntity, Long> {

    List<NftEntity> findByUserId(Long userId);
}
