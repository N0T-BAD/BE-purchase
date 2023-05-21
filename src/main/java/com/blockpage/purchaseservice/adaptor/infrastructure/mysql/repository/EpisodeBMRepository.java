package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.EpisodeBMEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeBMRepository extends JpaRepository<EpisodeBMEntity, Long> {

    List<EpisodeBMEntity> findAllByWebtoonId(Long webtoonId);
}
