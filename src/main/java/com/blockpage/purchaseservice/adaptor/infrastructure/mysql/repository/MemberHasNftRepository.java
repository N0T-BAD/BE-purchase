package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasNftEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasNftRepository extends JpaRepository<MemberHasNftEntity,Long> {

    List<MemberHasNftEntity> findByMemberId(String memberId);
}
