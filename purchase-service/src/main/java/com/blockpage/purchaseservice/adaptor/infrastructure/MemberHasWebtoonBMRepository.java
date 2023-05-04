package com.blockpage.purchaseservice.adaptor.infrastructure;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHasWebtoonBMRepository extends JpaRepository<MemberHasWebtoonBMEntity, Long> {

    List<MemberHasWebtoonBMEntity> findByMemberId(Long memberId);
}
