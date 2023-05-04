package com.blockpage.purchaseservice.adaptor.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<NftEntity, Long> {

    List<NftEntity> findByUserId(Long userId);
}
