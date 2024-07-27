SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `delete_reasons`;
DROP TABLE IF EXISTS `exhibitions_locations`;
DROP TABLE IF EXISTS `exhibitions_tags`;
DROP TABLE IF EXISTS `follows`;
DROP TABLE IF EXISTS `likes`;
DROP TABLE IF EXISTS `photos`;
DROP TABLE IF EXISTS `photos_tags`;
DROP TABLE IF EXISTS `photos_urls`;
DROP TABLE IF EXISTS `profile_urls`;
DROP TABLE IF EXISTS `tags`;
DROP TABLE IF EXISTS `temporary_exhibitions`;
DROP TABLE IF EXISTS `users_tags`;
DROP TABLE IF EXISTS `views`;
DROP TABLE IF EXISTS `withdraw_reasons`;
DROP TABLE IF EXISTS `exhibitions`;
DROP TABLE IF EXISTS `users`;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `users` (
                         `created_at` datetime(6) DEFAULT NULL,
                         `follower_count` bigint DEFAULT NULL,
                         `following_count` bigint DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `users_id` bigint NOT NULL AUTO_INCREMENT,
                         `view_count` bigint DEFAULT NULL,
                         `users_uuid` binary(16) DEFAULT NULL,
                         `introduce` varchar(20) DEFAULT NULL,
                         `email` varchar(50) NOT NULL,
                         `nickname` varchar(50) NOT NULL,
                         `biography` text,
                         `gender` enum('FEMALE','MALE') NOT NULL,
                         `role` enum('USER') DEFAULT NULL,
                         PRIMARY KEY (`users_id`),
                         UNIQUE KEY `UK_qo0mflg6xhle3gbakn017a8jt` (`users_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tags` (
                        `created_at` datetime(6) DEFAULT NULL,
                        `tags_id` bigint NOT NULL AUTO_INCREMENT,
                        `updated_at` datetime(6) DEFAULT NULL,
                        `name` varchar(10) NOT NULL,
                        `tags_uuid` binary(16) DEFAULT NULL,
                        PRIMARY KEY (`tags_id`),
                        UNIQUE KEY `UK_h1yo95gh58kwv76gq9qeukxyh` (`tags_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `temporary_exhibitions` (
                                         `created_at` datetime(6) DEFAULT NULL,
                                         `temporary_exhibitions_id` bigint NOT NULL AUTO_INCREMENT,
                                         `updated_at` datetime(6) DEFAULT NULL,
                                         `users_id` bigint DEFAULT NULL,
                                         `view_count` bigint DEFAULT NULL,
                                         `temporary_exhibitions_uuid` binary(16) DEFAULT NULL,
                                         `title` varchar(50) DEFAULT NULL,
                                         `description` text,
                                         `thumbnail_url` varchar(255) DEFAULT NULL,
                                         PRIMARY KEY (`temporary_exhibitions_id`),
                                         UNIQUE KEY `UK_dlyne539n3xhap9wiy6xl77lf` (`temporary_exhibitions_uuid`),
                                         KEY `temporary_exhibitions_fk_users_id` (`users_id`),
                                         CONSTRAINT `temporary_exhibitions_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `exhibitions` (
                               `created_at` datetime(6) DEFAULT NULL,
                               `exhibitions_id` bigint NOT NULL AUTO_INCREMENT,
                               `like_count` bigint DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `users_id` bigint DEFAULT NULL,
                               `view_count` bigint DEFAULT NULL,
                               `exhibitions_uuid` binary(16) DEFAULT NULL,
                               `title` varchar(50) DEFAULT NULL,
                               `description` text,
                               `thumbnail_url` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`exhibitions_id`),
                               UNIQUE KEY `UK_icif9r1xmm82u6bw8sxnql2nh` (`exhibitions_uuid`),
                               KEY `exhibitions_fk_users_id` (`users_id`),
                               CONSTRAINT `exhibitions_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comments` (
                            `is_like` bit(1) NOT NULL,
                            `comments_id` bigint NOT NULL AUTO_INCREMENT,
                            `created_at` datetime(6) DEFAULT NULL,
                            `exhibitions_id` bigint DEFAULT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `comments_uuid` binary(16) DEFAULT NULL,
                            `users_uuid` binary(16) NOT NULL,
                            `content` varchar(50) NOT NULL,
                            PRIMARY KEY (`comments_id`),
                            UNIQUE KEY `UK_bs6vgdjqok5v8uwchbaelhe93` (`comments_uuid`),
                            KEY `comments_fk_exhibitions_id` (`exhibitions_id`),
                            CONSTRAINT `comments_fk_exhibitions_id` FOREIGN KEY (`exhibitions_id`) REFERENCES `exhibitions` (`exhibitions_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `delete_reasons` (
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `delete_reason_id` bigint NOT NULL AUTO_INCREMENT,
                                  `updated_at` datetime(6) DEFAULT NULL,
                                  `email` varchar(50) NOT NULL,
                                  `delete_reason_value` enum('NOT_A_TARGET_USER','NO_INTENTION_AFTER_REFUND','OTHER','REJOINING_AFTER_DELETION','SERVICE_ERROR','TOO_COMPLEX') DEFAULT NULL,
                                  PRIMARY KEY (`delete_reason_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `exhibitions_locations` (
                                         `latitude` double NOT NULL,
                                         `longitude` double NOT NULL,
                                         `created_at` datetime(6) DEFAULT NULL,
                                         `exhibitions_id` bigint DEFAULT NULL,
                                         `exhibitions_locations_id` bigint NOT NULL AUTO_INCREMENT,
                                         `temporary_exhibitions_id` bigint DEFAULT NULL,
                                         `updated_at` datetime(6) DEFAULT NULL,
                                         `city` varchar(100) NOT NULL,
                                         `state` varchar(100) NOT NULL,
                                         `town` varchar(100) NOT NULL,
                                         PRIMARY KEY (`exhibitions_locations_id`),
                                         UNIQUE KEY `UK_mhsjkv8jeyriw7793i3sr6lrd` (`exhibitions_id`),
                                         UNIQUE KEY `UK_79m6rq2xbid8pmyh5ebktll3e` (`temporary_exhibitions_id`),
                                         CONSTRAINT `exhibitions_locations_fk_exhibitions_id` FOREIGN KEY (`exhibitions_id`) REFERENCES `exhibitions` (`exhibitions_id`) ON DELETE CASCADE,
                                         CONSTRAINT `exhibitions_locations_fk_temporary_exhibitions_id` FOREIGN KEY (`temporary_exhibitions_id`) REFERENCES `temporary_exhibitions` (`temporary_exhibitions_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `exhibitions_tags` (
                                    `created_at` datetime(6) DEFAULT NULL,
                                    `exhibitions_id` bigint DEFAULT NULL,
                                    `exhibitions_tags_id` bigint NOT NULL AUTO_INCREMENT,
                                    `tags_id` bigint DEFAULT NULL,
                                    `temporary_exhibitions_id` bigint DEFAULT NULL,
                                    `updated_at` datetime(6) DEFAULT NULL,
                                    PRIMARY KEY (`exhibitions_tags_id`),
                                    KEY `exhibitions_tags_fk_exhibitions_id` (`exhibitions_id`),
                                    KEY `exhibitions_tags_fk_tags_id` (`tags_id`),
                                    KEY `exhibitions_tags_fk_temporary_exhibitions_id` (`temporary_exhibitions_id`),
                                    CONSTRAINT `exhibitions_tags_fk_exhibitions_id` FOREIGN KEY (`exhibitions_id`) REFERENCES `exhibitions` (`exhibitions_id`) ON DELETE CASCADE,
                                    CONSTRAINT `exhibitions_tags_fk_tags_id` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`tags_id`) ON DELETE CASCADE,
                                    CONSTRAINT `exhibitions_tags_fk_temporary_exhibitions_id` FOREIGN KEY (`temporary_exhibitions_id`) REFERENCES `temporary_exhibitions` (`temporary_exhibitions_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `follows` (
                           `created_at` datetime(6) DEFAULT NULL,
                           `follows_id` bigint NOT NULL AUTO_INCREMENT,
                           `updated_at` datetime(6) DEFAULT NULL,
                           `users_id` bigint DEFAULT NULL,
                           `follower_uuid` binary(16) NOT NULL,
                           PRIMARY KEY (`follows_id`),
                           KEY `follows_fk_users_id` (`users_id`),
                           CONSTRAINT `follows_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `likes` (
                         `created_at` datetime(6) DEFAULT NULL,
                         `likes_id` bigint NOT NULL AUTO_INCREMENT,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `exhibitions_uuid` binary(16) NOT NULL,
                         `users_uuid` binary(16) NOT NULL,
                         PRIMARY KEY (`likes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `photos` (
                          `gray_scale` float DEFAULT NULL,
                          `created_at` datetime(6) DEFAULT NULL,
                          `exhibitions_id` bigint DEFAULT NULL,
                          `photos_id` bigint NOT NULL AUTO_INCREMENT,
                          `temporary_exhibitions_id` bigint DEFAULT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          `filters_uuid` binary(16) DEFAULT NULL,
                          `photos_uuid` binary(16) DEFAULT NULL,
                          PRIMARY KEY (`photos_id`),
                          KEY `photos_fk_exhibitions_id` (`exhibitions_id`),
                          KEY `photos_fk_temporary_exhibitions_id` (`temporary_exhibitions_id`),
                          CONSTRAINT `photos_fk_exhibitions_id` FOREIGN KEY (`exhibitions_id`) REFERENCES `exhibitions` (`exhibitions_id`) ON DELETE CASCADE,
                          CONSTRAINT `photos_fk_temporary_exhibitions_id` FOREIGN KEY (`temporary_exhibitions_id`) REFERENCES `temporary_exhibitions` (`temporary_exhibitions_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `photos_tags` (
                               `created_at` datetime(6) DEFAULT NULL,
                               `photos_id` bigint DEFAULT NULL,
                               `photos_tags_id` bigint NOT NULL AUTO_INCREMENT,
                               `tags_id` bigint DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`photos_tags_id`),
                               KEY `photos_tags_fk_photos_id` (`photos_id`),
                               KEY `photos_tags_fk_tags_id` (`tags_id`),
                               CONSTRAINT `photos_tags_fk_photos_id` FOREIGN KEY (`photos_id`) REFERENCES `photos` (`photos_id`) ON DELETE CASCADE,
                               CONSTRAINT `photos_tags_fk_tags_id` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`tags_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `photos_urls` (
                               `photos_id` bigint DEFAULT NULL,
                               `photos_urls_id` bigint NOT NULL AUTO_INCREMENT,
                               `photos_urls_uuid` binary(16) DEFAULT NULL,
                               `img_url` varchar(255) NOT NULL,
                               `s3_url` varchar(255) NOT NULL,
                               PRIMARY KEY (`photos_urls_id`),
                               UNIQUE KEY `UK_ny1g4l9bvm1eb6rhey30k22cg` (`photos_id`),
                               CONSTRAINT `photos_urls_fk_photos_id` FOREIGN KEY (`photos_id`) REFERENCES `photos` (`photos_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `profile_urls` (
                                `created_at` datetime(6) DEFAULT NULL,
                                `profile_urls_id` bigint NOT NULL AUTO_INCREMENT,
                                `updated_at` datetime(6) DEFAULT NULL,
                                `users_id` bigint DEFAULT NULL,
                                `img_url` varchar(250) NOT NULL DEFAULT 'default',
                                `s3_url` varchar(255) NOT NULL,
                                PRIMARY KEY (`profile_urls_id`),
                                UNIQUE KEY `UK_rs70ovr3s852k5kc731f30vc8` (`users_id`),
                                CONSTRAINT `profile_urls_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users_tags` (
                              `created_at` datetime(6) DEFAULT NULL,
                              `tags_id` bigint DEFAULT NULL,
                              `updated_at` datetime(6) DEFAULT NULL,
                              `users_id` bigint DEFAULT NULL,
                              `users_tags_id` bigint NOT NULL AUTO_INCREMENT,
                              PRIMARY KEY (`users_tags_id`),
                              KEY `users_tags_fk_tags_id` (`tags_id`),
                              KEY `users_tags_fk_users_id` (`users_id`),
                              CONSTRAINT `users_tags_fk_tags_id` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`tags_id`) ON DELETE CASCADE,
                              CONSTRAINT `users_tags_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `views` (
                         `exhibitions_id` bigint DEFAULT NULL,
                         `photos_id` bigint DEFAULT NULL,
                         `users_id` bigint DEFAULT NULL,
                         `views_id` bigint NOT NULL AUTO_INCREMENT,
                         PRIMARY KEY (`views_id`),
                         KEY `views_fk_exhibitions_id` (`exhibitions_id`),
                         KEY `views_fk_photos_id` (`photos_id`),
                         KEY `views_fk_users_id` (`users_id`),
                         CONSTRAINT `views_fk_exhibitions_id` FOREIGN KEY (`exhibitions_id`) REFERENCES `exhibitions` (`exhibitions_id`) ON DELETE CASCADE,
                         CONSTRAINT `views_fk_photos_id` FOREIGN KEY (`photos_id`) REFERENCES `photos` (`photos_id`) ON DELETE CASCADE,
                         CONSTRAINT `views_fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `withdraw_reasons` (
                                    `created_at` datetime(6) DEFAULT NULL,
                                    `updated_at` datetime(6) DEFAULT NULL,
                                    `withdraw_reasons_id` bigint NOT NULL AUTO_INCREMENT,
                                    `reason` text NOT NULL,
                                    PRIMARY KEY (`withdraw_reasons_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
