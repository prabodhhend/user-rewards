package org.acko.smartlife.premiumCalculator;


import org.acko.smartlife.constants.RewardPointType;
import org.acko.smartlife.dao.jpa.PremiumConfigRepo;
import org.acko.smartlife.dao.jpa.PremiumDeductHistoryRepo;
import org.acko.smartlife.models.dao.jpa.PremiumConfig;
import org.acko.smartlife.models.dao.jpa.PremiumDeductHistory;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class PremiumCalculatorService {


    @Autowired
    RewardService rewardService;

    @Autowired
    PremiumConfigRepo premiumConfigRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    PremiumDeductHistoryRepo premiumDeductHistoryRepo;


    public List<PremiumDeductHistory> getPremiumHistory(Long userId){

       return premiumDeductHistoryRepo.findByUserId(userId);
    }


    public Double getNextPremium(Long userId){


       PremiumConfig premiumConfig =  premiumConfigRepo.findByUserId(userId);
        RewardResponse rewardResponse =  rewardService.getSummary(userId);
        Double totalRewards = rewardResponse.getPoints() - rewardResponse.getRedeemed();
       return premiumConfig.getBasePremium() - (totalRewards * 0.5) < 0? 0 : premiumConfig.getBasePremium() - (totalRewards * 0.5);

    }

    public void premiumDeductor(){


      //  List<PremiumConfig> premiumConfigs =  premiumConfigRepo.findAll();
        Pair<String, String> datePair = getDateRange();



        List<PremiumConfig> premiumConfigs  = entityManager.createNativeQuery("select pc.* from premium_config pc \n" +
                "left outer join premium_deduct_history pdh on pc.user_id =pdh.user_id \n" +
                "where  pdh.created_at is null OR (pdh.created_at not between '"+datePair.getFirst() + "' and '" + datePair.getSecond() + "')", PremiumConfig.class).getResultList();


        premiumConfigs.forEach(
                premiumConfig -> {
                    RewardResponse rewardResponse = rewardService.getSummary(premiumConfig.getUserId());
                    Double totalRewards = rewardResponse.getPoints() - rewardResponse.getRedeemed();
                    Double rewardMoney = totalRewards * 0.5;


                    Double totalMoneyDeducted;
                    Double totalRewardsDeducted;

                    if(rewardMoney > premiumConfig.getBasePremium()){

                        totalMoneyDeducted = 0.0;
                        totalRewardsDeducted = totalRewards - premiumConfig.getBasePremium().longValue() * 2;
                    }
                    else {
                        totalRewardsDeducted = totalRewards;
                        totalMoneyDeducted = premiumConfig.getBasePremium() - rewardMoney;
                    }
                    premiumDeductor(premiumConfig, rewardResponse, totalMoneyDeducted, totalRewardsDeducted);

                }

        );


    }

    @Transactional
    private void premiumDeductor(PremiumConfig premiumConfig, RewardResponse rewardResponse, Double totalMoneyDeducted, Double totalRewardsDeducted) {
        rewardService.updateRewards(rewardResponse.getRewardId(), RewardPointType.REDEEM,totalRewardsDeducted);
        premiumDeductHistoryRepo.save(new PremiumDeductHistory(premiumConfig.getUserId(),totalMoneyDeducted,totalRewardsDeducted));
    }

    public Pair<String, String> getDateRange() {
        Date begining, end;

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        return Pair.of(sdf.format(begining), sdf.format(end));
    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }



}
