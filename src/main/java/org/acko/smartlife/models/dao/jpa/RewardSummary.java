package org.acko.smartlife.models.dao.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author prabodh.hend
 */
@Data
@Entity
@Table(name = "rewards_summary", indexes = {@Index(name = "idx_user_id", columnList = "user_id")})
@Where(clause = "is_deleted = 0")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardSummary extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reward_id")
    private String rewardId;

    @Column(name = "total_rewards")
    private Double totalRewards = 0D;

    @Column(name = "total_redeemed")
    private Double totalRedeemed = 0D;

}
