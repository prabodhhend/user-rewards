package org.acko.smartlife.service;

import org.acko.smartlife.constants.RewardPointType;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.models.dto.UpdateRewardsRequest;

/**
 * @author prabodh.hend
 */
public interface RewardService {

    RewardResponse getSummary(Long userId);

    boolean updateRewards(String rewardId, RewardPointType type, Double points);

    boolean updateRewards(UpdateRewardsRequest request);
}
