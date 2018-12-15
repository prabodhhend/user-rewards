package org.acko.smartlife.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.models.dto.UpdateRewardsRequest;
import org.acko.smartlife.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author prabodh.hend
 */
@RestController
@RequestMapping("api/v1")
@Slf4j
@Api(value = "RewardController", description = "Reward Apis", tags = {"Reward Apis"})
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/rewards/{userId}")
    public ResponseEntity<RewardResponse> getRewards(@PathVariable("userId") Long userId) {
        log.info("Fetching reward details for user:{}", userId);
        RewardResponse response = rewardService.getSummary(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/rewards/update")
    public ResponseEntity updateRewards(@RequestBody List<UpdateRewardsRequest> requestList) {
        log.info("Updating Rewards for userId: {}", requestList.get(0).getUserId());
        rewardService.updateRewards(requestList);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
