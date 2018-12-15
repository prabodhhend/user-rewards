package org.acko.smartlife.models.dao.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "premium_deduct_history")
@Where(clause = "is_deleted = 0")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PremiumDeductHistory extends BaseEntity{


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "money_deducted")
    private Double moneyDeducted;

    @Column(name = "rewards_redeemed")
    private Double rewardsRedeemed;

}
