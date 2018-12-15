package org.acko.smartlife.service;

import org.acko.smartlife.constants.RewardPointType;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.models.dto.UpdateRewardsRequest;

import java.util.List;

/**
 * @author prabodh.hend
 */
public interface RewardService {

    RewardResponse getSummary(Long userId);

    boolean updateRewards(String rewardId, RewardPointType type, Double points);

    void updateRewards(List<UpdateRewardsRequest> request);
}
