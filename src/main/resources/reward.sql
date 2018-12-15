create database rewards;

use rewards;

CREATE TABLE `rewards_summary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `reward_id` varchar(128) NOT NULL,
  `total_rewards` DECIMAL( 10, 2 ),
  `total_redeemed` DECIMAL( 10, 2 ),
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_reward_id` (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `rewards_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `reward_id`varchar(128) NOT NULL,
  `added` DECIMAL( 10, 2 ),
  `redeemed` DECIMAL( 10, 2 ),
  `type` enum('ADD','REDEEM') NOT NULL DEFAULT 'ADD',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_reward_id` (`reward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


create table premium_config(
 id long AUTO_INCREMENT,
 user_id LONG ,
 base_premium double,
  is_deleted tinyint(1) ,
   created_at timestamp ,
   created_by varchar(128)  ,
   updated_at timestamp ,
   updated_by varchar(128)

 )

 create table premium_deduct_history(
 id long AUTO_INCREMENT,
 user_id LONG ,
 money_deducted double,
 rewards_redeemed int,
  is_deleted tinyint(1) ,
    created_at timestamp ,
    created_by varchar(128),
    updated_at timestamp ,
    updated_by varchar(128)
 )