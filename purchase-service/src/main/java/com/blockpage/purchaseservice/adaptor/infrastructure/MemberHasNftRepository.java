package com.blockpage.purchaseservice.adaptor.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasNftRepository extends JpaRepository<MemberHasNftEntity,Long> {

    List<MemberHasNftEntity> findByMemberId(Long memberId);
}
