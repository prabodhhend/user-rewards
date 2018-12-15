package org.acko.smartlife.models.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author prabodh.hend
 */
@Data
public class RewardResponse {

    private String rewardId;
    private Long userId;
    private Date date;
    private Double points;
    private Double redeemed;
    private Double earnedToday;
}
