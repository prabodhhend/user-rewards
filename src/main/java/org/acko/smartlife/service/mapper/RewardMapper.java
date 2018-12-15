package org.acko.smartlife.service.mapper;

import org.acko.smartlife.models.dao.jpa.RewardSummary;
import org.acko.smartlife.models.dto.RewardResponse;

/**
 * @author prabodh.hend
 */
public class RewardMapper {

    public static RewardResponse map(RewardSummary summary) {
        RewardResponse response = new RewardResponse();
        if (null != summary) {
            response.setDate(summary.getUpdatedAt());
            response.setPoints(summary.getTotalRewards());
            response.setRedeemed(summary.getTotalRedeemed());
            response.setRewardId(summary.getRewardId());
            response.setUserId(summary.getUserId());
        }
        return response;
    }
}
