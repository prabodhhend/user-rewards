package org.acko.smartlife.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.acko.smartlife.models.dto.RewardResponse;
import org.acko.smartlife.premiumCalculator.PremiumCalculatorService;
import org.acko.smartlife.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
@Api(value = "PremiumController", description = "Premium Apis", tags = {"Premium Apis"})
public class PremiumCalculatorController {

    @Autowired
    private PremiumCalculatorService premiumCalculatorService;

    @GetMapping("/premium/{userId}/")
    public ResponseEntity<Double> getRewards(@PathVariable("userId") Long userId) {
        log.info("Fetching premium details for user:{}", userId);
        Double nextPremium = premiumCalculatorService.getNextPremium(userId);
        return new ResponseEntity<>(nextPremium, HttpStatus.OK);
    }

    @GetMapping("/premium/deduct/")
    public ResponseEntity deductPremium() {
        log.info("deduct premium for this month");
         premiumCalculatorService.premiumDeductor();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
