package com.blockpage.purchaseservice.adaptor.infrastructure.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.WebtoonBMEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonBMRepository extends JpaRepository<WebtoonBMEntity, Long> {

    List<WebtoonBMEntity> findAllByWebtoonId(Long webtoonId);
}
