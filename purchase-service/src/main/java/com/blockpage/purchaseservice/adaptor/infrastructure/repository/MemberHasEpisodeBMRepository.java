package com.blockpage.purchaseservice.adaptor.infrastructure.repository;


import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasEpisodeBMEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasEpisodeBMRepository extends JpaRepository<MemberHasEpisodeBMEntity, Long> {

    List<MemberHasEpisodeBMEntity> findByMemberId(Long memberId);
}
