-- 태그 전체 삽입
INSERT INTO tags (tags_uuid, name) VALUES
                                                              (UNHEX(REPLACE('d20e2654-3c4a-4ebe-b1c9-5695ac2a6207', '-', '')), '따뜻한'),
                                                              (UNHEX(REPLACE('fe96c294-b5f3-425e-a6de-8cc1b13beb5a', '-', '')), '부드러운'),
                                                              (UNHEX(REPLACE('118ccbfb-8caf-498b-913a-16a315b3a859', '-', '')), '평화로운'),
                                                              (UNHEX(REPLACE('4a0db2eb-f4bc-4fa3-ae47-8381ed0da1ab', '-', '')), '차가운'),
                                                              (UNHEX(REPLACE('ae4a3cee-f7e3-48a1-8b0a-eb4d177b2267', '-', '')), '세련된'),
                                                              (UNHEX(REPLACE('1f479a8d-dab2-4d95-96c9-73d5f7382a01', '-', '')), '자연스러운'),
                                                              (UNHEX(REPLACE('8969e7f1-2d1e-4a6d-b234-73c2aa7b24ff', '-', '')), '클래식한'),
                                                              (UNHEX(REPLACE('9b11a16b-6786-4a28-8273-ff9e06b80318', '-', '')), '쾌활한'),
                                                              (UNHEX(REPLACE('35009d25-65e1-48da-800e-44be42bf3b4e', '-', '')), '우아한'),
                                                              (UNHEX(REPLACE('775f2020-070f-4ba1-b601-b456b4a8c165', '-', '')), '잔잔한'),
                                                              (UNHEX(REPLACE('5b3a7d95-529d-42a4-a9eb-9e3fd3c42933', '-', '')), '풍요로운'),
                                                              (UNHEX(REPLACE('e8e34cc1-27e1-4875-b474-90c3c1c2a7bb', '-', '')), '짙은'),
                                                              (UNHEX(REPLACE('c27a3a02-134b-41de-a50e-27d722d2fbbd', '-', '')), '빈티지한'),
                                                              (UNHEX(REPLACE('f9abf63a-bfd3-4960-840e-45841a1c50d3', '-', '')), '화사한'),
                                                              (UNHEX(REPLACE('3c0c90ff-f775-474a-bc6d-83f1c8c30536', '-', '')), '모던한'),
                                                              (UNHEX(REPLACE('8a3eb59d-263e-486a-aa9c-c672d2599a8b', '-', '')), '수수한'),
                                                              (UNHEX(REPLACE('45633dc1-34d0-4a2c-8cc9-0bf7ecbb6e3c', '-', '')), '사랑스러운'),
                                                              (UNHEX(REPLACE('8954a54d-2e9a-4a6e-b63e-081119c4a93c', '-', '')), '캐주얼한'),
                                                              (UNHEX(REPLACE('89fde358-fd6b-4d6b-ba07-057e6c4e4b8b', '-', '')), '자유분방한'),
                                                              (UNHEX(REPLACE('05e59ff6-7d1c-4497-850a-1683de7e7e59', '-', '')), '다채로운'),
                                                              (UNHEX(REPLACE('1b2f7f85-5d71-4881-81ad-70a1b2d1c1a0', '-', '')), '차분한'),
                                                              (UNHEX(REPLACE('61715019-1f05-45e6-91e2-13b50d818efb', '-', '')), '신비로운'),
                                                              (UNHEX(REPLACE('b06da443-52c2-4398-9bdf-6a7f3f14f29f', '-', '')), '순수한'),
                                                              (UNHEX(REPLACE('c5a5ff7b-0b40-4683-b796-5c295b1908a5', '-', '')), '고요한'),
                                                              (UNHEX(REPLACE('ec2b0244-e37c-4fd0-8aee-c11c831124b3', '-', '')), '고급스러운')

ON DUPLICATE KEY UPDATE tags_uuid = tags_uuid;

INSERT INTO users (users_uuid, nickname, email, role, gender, introduce, biography, created_at, updated_at, view_count, support_count) VALUES
    (UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test_user', 'hsh111366@naver.com', 'USER', 'MALE', '', '', NOW(), NOW(), 0, 0)
    ON DUPLICATE KEY UPDATE users_uuid = users_uuid;