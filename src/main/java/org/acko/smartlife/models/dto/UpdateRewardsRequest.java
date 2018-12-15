package org.acko.smartlife.models.dto;

import lombok.Data;
import org.acko.smartlife.constants.RewardPointType;

import javax.validation.constraints.NotNull;

/**
 * @author prabodh.hend
 */
@Data
public class UpdateRewardsRequest {

    @NotNull
    private Long userId;
    @NotNull
    private RewardPointType type;
    private Double points = 0D;
}
