package org.acko.smartlife.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.acko.smartlife.constants.RewardPointType;
import org.acko.smartlife.dao.jpa.RewardDetailsRepository;
import org.acko.smartlife.dao.jpa.RewardSummaryRepository;
import org.acko.smartlife.models.dao.jpa.RewardDetails;
import org.acko.smartlife.models.dao.jpa.RewardSummary;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.models.dto.UpdateRewardsRequest;
import org.acko.smartlife.service.RewardService;
import org.acko.smartlife.service.integration.UserService;
import org.acko.smartlife.service.mapper.RewardMapper;
import org.acko.smartlife.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author prabodh.hend
 */
@Service
@Slf4j
public class RewardServiceImpl implements RewardService {

    @Autowired
    private UserService userService;

    @Autowired
    private RewardSummaryRepository rewardSummaryRepository;

    @Autowired
    private RewardDetailsRepository rewardDetailsRepository;

    @Override
    public RewardResponse getSummary(Long userId) {
        userService.validate(userId);
        RewardSummary summary = rewardSummaryRepository.findByUserId(userId);

        Date from = DateTimeUtil.getTodayMorning();
        Date to = DateTimeUtil.getTodayNight();

        RewardDetails rewardDetails = rewardDetailsRepository.findLatestByRewardIdAndType(summary.getRewardId(),
                RewardPointType.ADD.toString(), DateTimeUtil.getYYYYMMddHHmmss(from), DateTimeUtil.getYYYYMMddHHmmss(to));

        return RewardMapper.map(summary, rewardDetails);
    }

    @Override
    @Transactional
    public boolean updateRewards(String rewardId, RewardPointType type, Double points) {
        boolean flag = false;
        RewardSummary summary = rewardSummaryRepository.findByRewardId(rewardId);

        if (null != summary) {
            RewardDetails rewardDetails = null;
            switch (type) {
                case ADD:
                    rewardDetails = RewardDetails.builder().rewardId(rewardId).added(points).redeemed(0D).type(type).build();
                    summary.setTotalRewards(summary.getTotalRewards() + points);
                    break;
                case REDEEM:
                    rewardDetails = RewardDetails.builder().rewardId(rewardId).added(0D).redeemed(points).type(type).build();
                    summary.setTotalRedeemed(summary.getTotalRedeemed() + points);
                    break;
            }
            rewardDetails = rewardDetailsRepository.save(rewardDetails);
            if (null != rewardDetails) {
                flag = true;
                rewardSummaryRepository.save(summary);
            }
        }
        return flag;
    }

    @Override
    public void updateRewards(List<UpdateRewardsRequest> rewardsRequests) {
        boolean flag = false;
        rewardsRequests.stream().forEach(request -> {
            RewardSummary summary = rewardSummaryRepository.findByUserId(request.getUserId());
            if (!StringUtils.isEmpty(request.getUserId()) && request.getPoints() > 0D) {
                 this.updateRewards(summary.getRewardId(), request.getType(), request.getPoints());
            } else {
                throw new RuntimeException("Invalid request");
            }
        });
    }
}
