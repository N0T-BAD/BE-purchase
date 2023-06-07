package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;


import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasEpisodeBMEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasEpisodeBMRepository extends JpaRepository<MemberHasEpisodeBMEntity, Long> {

    List<MemberHasEpisodeBMEntity> findByMemberIdAndWebtoonIdAndFree(String memberId, Long webtoonId, Boolean free);

    List<MemberHasEpisodeBMEntity> findAllByRegisterTimeBetween(LocalDateTime start, LocalDateTime end);

    List<MemberHasEpisodeBMEntity> findByMemberIdAndFree(String memberId, Boolean free);
}
