INSERT INTO rewards_summary (id, user_id, reward_id, total_rewards, total_redeemed, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
(1, 12, '1212', 100.00, 90.00, 0, '2018-12-15 19:02:22', NULL, '2018-12-15 19:02:22', NULL);

INSERT INTO rewards_details (id, reward_id, added, redeemed, type, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
(1, '1212', 100.00, 0.00, 'ADD', 0, '2018-12-15 19:02:40', NULL, '2018-12-15 19:02:40', NULL),
(2, '1212', 0.00, 90.00, 'REDEEM', 0, '2018-12-15 19:02:50', NULL, '2018-12-15 19:02:50', NULL);

insert into premium_config values(1,12, 2000,0,'2018-12-15 19:02:40', NULL, '2018-12-15 19:02:40', NULL)