package org.acko.smartlife.premiumCalculator;


import org.acko.smartlife.constants.RewardPointType;
import org.acko.smartlife.dao.jpa.PremiumConfigRepo;
import org.acko.smartlife.models.dao.jpa.PremiumConfig;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PremiumCalculatorService {


    @Autowired
    RewardService rewardService;

    @Autowired
    PremiumConfigRepo premiumConfigRepo;


    public Double getNextPremium(Long userId){


       PremiumConfig premiumConfig =  premiumConfigRepo.findByUserId(userId);
        RewardResponse rewardResponse =  rewardService.getSummary(userId);
        Double totalRewards = rewardResponse.getPoints() - rewardResponse.getRedeemed();
       return premiumConfig.getBasePremium() - (totalRewards * 0.5) < 0? 0 : premiumConfig.getBasePremium() - (totalRewards * 0.5);

    }

    public void premiumDeductor(){


        List<PremiumConfig> premiumConfigs =  premiumConfigRepo.findAll();

        premiumConfigs.forEach(
                premiumConfig -> {
                    RewardResponse rewardResponse = rewardService.getSummary(premiumConfig.getUserId());
                    Double totalRewards = rewardResponse.getPoints() - rewardResponse.getRedeemed();
                    Double totalRewardsNeededForPremium  =  premiumConfig.getBasePremium() * 2;
                    Double remainingRewards =   totalRewards - totalRewardsNeededForPremium;
                            if(remainingRewards < 0)
                            {
                                rewardService.updateRewards(rewardResponse.getRewardId(), RewardPointType.REDEEM,totalRewards);
                            }
                            else {

                                rewardService.updateRewards(rewardResponse.getRewardId(), RewardPointType.REDEEM,totalRewardsNeededForPremium);
                            }
                }

        );


    }



}
