package com.blockpage.purchaseservice.adaptor.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasProfileSkinRepository extends JpaRepository<MemberHasProfileSkinEntity, Long> {

    List<MemberHasProfileSkinEntity> findByMemberId(Long memberId);
}
