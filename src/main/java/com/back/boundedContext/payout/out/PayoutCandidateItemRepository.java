package com.back.boundedContext.payout.out;

import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PayoutCandidateItemRepository extends JpaRepository<PayoutCandidateItem, Integer> {
    /**
     * SELECT *
     * FROM payout_candidate_item
     * WHERE payout_item_id IS NULL
     *   AND payment_date < :paymentDate
     * ORDER BY payee_id ASC, id ASC
     * LIMIT :pageSize OFFSET :offset;
     *
     * 1. 아직 정산 안 된 아이템 조회
     * 2. 일정 시점 이전 결제된 것만
     * 3. 판매자별로 묶어서
     * 4. 일정 개수씩 처리
     */

    List<PayoutCandidateItem> findByPayoutItemIsNullAndPaymentDateBeforeOrderByPayeeAscIdAsc(LocalDateTime paymentDate, Pageable pageable);
}
