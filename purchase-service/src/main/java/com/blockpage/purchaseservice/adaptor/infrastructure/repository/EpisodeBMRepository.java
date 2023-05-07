package com.blockpage.purchaseservice.adaptor.infrastructure.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.EpisodeBMEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeBMRepository extends JpaRepository<EpisodeBMEntity, Long> {

    List<EpisodeBMEntity> findAllByWebtoonId(Long webtoonId);
}
