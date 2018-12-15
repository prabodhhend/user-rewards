package org.acko.smartlife.dao.jpa;

import org.acko.smartlife.models.dao.jpa.RewardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author prabodh.hend
 */
@Repository
public interface RewardDetailsRepository extends JpaRepository<RewardDetails, String> {

    List<RewardDetails> findByRewardId(String rewardId);

    @Query(value = "select * from `rewards_details` where `reward_id`= :rewardId and `type` = :type and " +
            "`is_deleted` = 0 and  `updated_at` between :from AND :to " +
            " order by `id` desc;", nativeQuery = true)
    List<RewardDetails> findLatestByRewardIdAndType(@Param("rewardId") String rewardId, @Param("type") String type,
                                              @Param("from") String from, @Param("to") String to);
}
