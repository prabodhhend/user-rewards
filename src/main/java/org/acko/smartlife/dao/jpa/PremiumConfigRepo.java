package org.acko.smartlife.dao.jpa;

import org.acko.smartlife.models.dao.jpa.PremiumConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PremiumConfigRepo extends JpaRepository<PremiumConfig,Integer>{


   PremiumConfig findByUserId(String userId);
}
