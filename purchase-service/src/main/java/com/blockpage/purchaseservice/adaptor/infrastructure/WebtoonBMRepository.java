package com.blockpage.purchaseservice.adaptor.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonBMRepository extends JpaRepository<WebtoonBMEntity, Long> {

    List<WebtoonBMEntity> findAllByWebtoonId(Long webtoonId);
}
