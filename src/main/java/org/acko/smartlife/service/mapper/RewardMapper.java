package org.acko.smartlife.service.mapper;

import org.acko.smartlife.models.dao.jpa.RewardDetails;
import org.acko.smartlife.models.dao.jpa.RewardSummary;
import org.acko.smartlife.models.dto.RewardResponse;

import java.util.List;

/**
 * @author prabodh.hend
 */
public class RewardMapper {

    public static RewardResponse map(RewardSummary summary, List<RewardDetails> rewardDetailsList) {
        RewardResponse response = new RewardResponse();
        if (null != summary) {
            response.setDate(summary.getUpdatedAt());
            response.setPoints(summary.getTotalRewards());
            response.setRedeemed(summary.getTotalRedeemed());
            response.setRewardId(summary.getRewardId());
            response.setUserId(summary.getUserId());
            response.setEarnedToday(getEarnedToday(rewardDetailsList));
        }
        return response;
    }

    private static Double getEarnedToday(List<RewardDetails> rewardDetailsList) {
        Double earned = 0D;
        if (null != rewardDetailsList && !rewardDetailsList.isEmpty()) {
            for (RewardDetails rewardDetails : rewardDetailsList) {
                earned += rewardDetails.getAdded();
            }
        }
        return earned;
    }
}
