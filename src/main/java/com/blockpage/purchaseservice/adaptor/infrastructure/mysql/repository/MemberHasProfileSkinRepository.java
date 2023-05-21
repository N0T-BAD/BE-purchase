package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasProfileSkinRepository extends JpaRepository<MemberHasProfileSkinEntity, Long> {

    List<MemberHasProfileSkinEntity> findByMemberId(Long memberId);
}
