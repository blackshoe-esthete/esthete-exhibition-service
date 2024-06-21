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

-- 디폴트 유저 삽입
INSERT INTO users (users_id, users_uuid, nickname, email, role, gender, introduce, biography, created_at, updated_at, view_count, support_count) VALUES
    (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test_user', 'hsh111366@naver.com', 'USER', 'MALE', '안녕하세요.', '', NOW(), NOW(), 0, 10),
    (2, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'test_user2', 'asefa1354@naver.com', 'USER', 'FEMALE', '반가워요.', '', NOW(), NOW(), 0, 20),
    (3, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'test_user3', 'riquer546@naver.com', 'USER', 'MALE', '행복해요.', '', NOW(), NOW(), 0, 5),
    (4, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'test_user4', 'fpdogsj44@naver.com', 'USER', 'FEMALE', '즐거워요.', '', NOW(), NOW(), 0, 100),
    (5, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'test_user5', 'sfas46587@naver.com', 'USER', 'MALE', '화가나요.', '', NOW(), NOW(), 0, 1)
    ON DUPLICATE KEY UPDATE users_uuid = users_uuid;

-- 디폴트 임시저장 전시 삽입
INSERT INTO temporary_exhibitions (temporary_exhibitions_uuid, users_id, thumbnail_url, created_at) VALUES
    (UNHEX(REPLACE('33a7b24b-c1ac-7252-ca7f-c6706daf22aa', '-', '')), 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', NOW())
    ON DUPLICATE KEY UPDATE temporary_exhibitions_uuid = temporary_exhibitions_uuid;

# -- 디폴트 임시저장 전시 사진 삽입
# INSERT INTO temporary_exhibitions_photos (temporary_exhibitions_id, img_url, s3_url, created_at) VALUES
#     (1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', NOW()),
#     (1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1235.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1235.jpeg', NOW()),
#     (1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1236.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1236.jpeg', NOW())
#     ON DUPLICATE KEY UPDATE temporary_exhibitions_photos_id = temporary_exhibitions_photos_id;

-- 디폴트 전시 삽입
INSERT INTO exhibitions (exhibitions_id, users_id, view_count, exhibitions_uuid, title, description, thumbnail_url) VALUES
    (1, 1, 203, UNHEX(REPLACE('d8265394-573e-4d5e-baf0-8b75fe10896e', '-', '')), '봄', '혼자여행', '썸네일 url이라고 생각하세요'),
    (2, 1, 152, UNHEX(REPLACE('b35b9358-df1d-4d9c-b362-cb51e94e5e25', '-', '')), '여름', '혼자여행', '썸네일 url이라고 생각하세요'),
    (3, 1, 313, UNHEX(REPLACE('5f2e2f9d-0411-437e-91e5-7922a35b1044', '-', '')), '가을', '혼자여행', '썸네일 url이라고 생각하세요'),
    (4, 2, 22, UNHEX(REPLACE('d61d5f71-f5bc-4ab3-92c8-62b1de207102', '-', '')), '겨울', '혼자여행', '썸네일 url이라고 생각하세요'),
    (5, 2, 15, UNHEX(REPLACE('74148b2b-9f0e-4650-8e94-3ec6ccde34a6', '-', '')), '부산', '혼자여행', '썸네일 url이라고 생각하세요'),
    (6, 2, 1000, UNHEX(REPLACE('f5f16b49-12ac-4745-aac5-4b1b269eabcb', '-', '')), '서울', '혼자여행', '썸네일 url이라고 생각하세요'),
    (7, 3, 1235, UNHEX(REPLACE('a3087e10-7da2-4760-a82d-d80c4397e0e1', '-', '')), '대전', '혼자여행', '썸네일 url이라고 생각하세요'),
    (8, 3, 2, UNHEX(REPLACE('f8e0c2d1-6a0d-47cb-80f2-03d28acdd27f', '-', '')), '울산', '혼자여행', '썸네일 url이라고 생각하세요'),
    (9, 3, 56, UNHEX(REPLACE('ce7d48b4-ef0e-4c6d-bb74-b8be6d9f2874', '-', '')), '대구', '혼자여행', '썸네일 url이라고 생각하세요')
    ON DUPLICATE KEY UPDATE exhibitions_id = exhibitions_id;

-- 디폴트 전시 태그 삽입
INSERT INTO exhibitions_tags (exhibitions_tags_id, exhibitions_id, tags_id)
VALUES
    (1, 1, 1),
    (2, 1, 3),
    (3, 1, 13),
    (4, 2, 2),
    (5, 2, 4),
    (6, 2, 12),
    (7, 3, 5),
    (8, 3, 8),
    (9, 3, 25),
    (10, 4, 24),
    (11, 4, 11),
    (12, 4, 9),
    (13, 5, 15),
    (14, 5, 2),
    (15, 5, 5),
    (16, 6, 21),
    (17, 6, 20),
    (18, 6, 10),
    (19, 7, 6),
    (20, 7, 7),
    (21, 7, 14)
    ON DUPLICATE KEY UPDATE exhibitions_tags_id = exhibitions_tags_id;