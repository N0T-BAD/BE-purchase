package com.blockpage.purchaseservice.adaptor.infrastructure.mysql.repository;

import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.MemberHasProfileSkinEntity;
import com.blockpage.purchaseservice.adaptor.infrastructure.mysql.entity.ProfileSkinEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasProfileSkinRepository extends JpaRepository<MemberHasProfileSkinEntity, Long> {

    List<MemberHasProfileSkinEntity> findByMemberId(String memberId);

    Optional<MemberHasProfileSkinEntity> findByMemberIdAndProfileSkinEntity(String memberId, ProfileSkinEntity profileSkinEntity);
}
