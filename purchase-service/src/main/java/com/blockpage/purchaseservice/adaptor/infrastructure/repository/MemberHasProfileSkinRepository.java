package com.blockpage.purchaseservice.adaptor.infrastructure.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.entity.MemberHasProfileSkinEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasProfileSkinRepository extends JpaRepository<MemberHasProfileSkinEntity, Long> {

    List<MemberHasProfileSkinEntity> findByMemberId(Long memberId);
}
