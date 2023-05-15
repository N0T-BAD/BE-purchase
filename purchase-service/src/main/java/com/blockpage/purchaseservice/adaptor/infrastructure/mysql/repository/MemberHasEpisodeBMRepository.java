package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;


import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasEpisodeBMRepository extends JpaRepository<MemberHasEpisodeBMEntity, Long> {

    List<MemberHasEpisodeBMEntity> findByMemberIdAndWebtoonId(Long memberId, Long webtoonId);
}
