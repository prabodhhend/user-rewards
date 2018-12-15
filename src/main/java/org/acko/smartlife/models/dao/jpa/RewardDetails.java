package org.acko.smartlife.models.dao.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acko.smartlife.constants.RewardPointType;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author prabodh.hend
 */
@Data
@Entity
@Table(name = "rewards_details", indexes = {@Index(name = "idx_reward_id", columnList = "reward_id")})
@Where(clause = "is_deleted = 0")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardDetails extends BaseEntity {

    @Column(name = "reward_id")
    private String rewardId;

    @Column(name = "added")
    private Double added = 0D;

    @Column(name = "redeemed")
    private Double redeemed = 0D;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RewardPointType type;

}
