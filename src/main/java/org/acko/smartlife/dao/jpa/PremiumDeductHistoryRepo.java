package org.acko.smartlife.dao.jpa;

import org.acko.smartlife.models.dao.jpa.PremiumDeductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PremiumDeductHistoryRepo extends JpaRepository<PremiumDeductHistory,Integer> {


    public List<PremiumDeductHistory> findByUserId(Long userId);
}
