-- 태그 전체 삽입
INSERT INTO tags (tags_id, tags_uuid, name) VALUES
                                                              (1, UNHEX(REPLACE('d20e2654-3c4a-4ebe-b1c9-5695ac2a6207', '-', '')), '초상화'),
                                                              (2, UNHEX(REPLACE('fe96c294-b5f3-425e-a6de-8cc1b13beb5a', '-', '')), '풍경'),
                                                              (3, UNHEX(REPLACE('118ccbfb-8caf-498b-913a-16a315b3a859', '-', '')), '거리'),
                                                              (4, UNHEX(REPLACE('4a0db2eb-f4bc-4fa3-ae47-8381ed0da1ab', '-', '')), '음식'),
                                                              (5, UNHEX(REPLACE('ae4a3cee-f7e3-48a1-8b0a-eb4d177b2267', '-', '')), '패션'),
                                                              (6, UNHEX(REPLACE('1f479a8d-dab2-4d95-96c9-73d5f7382a01', '-', '')), '건축'),
                                                              (7, UNHEX(REPLACE('8969e7f1-2d1e-4a6d-b234-73c2aa7b24ff', '-', '')), '야경'),
                                                              (8, UNHEX(REPLACE('9b11a16b-6786-4a28-8273-ff9e06b80318', '-', '')), '스포츠'),
                                                              (9, UNHEX(REPLACE('35009d25-65e1-48da-800e-44be42bf3b4e', '-', '')), '저널리즘'),
                                                              (10, UNHEX(REPLACE('775f2020-070f-4ba1-b601-b456b4a8c165', '-', '')), '야생'),
                                                              (11, UNHEX(REPLACE('5b3a7d95-529d-42a4-a9eb-9e3fd3c42933', '-', '')), '미술')
ON DUPLICATE KEY UPDATE tags_uuid = tags_uuid;


-- 디폴트 유저 삽입
INSERT INTO users (users_id, users_uuid, nickname, email, role, gender, introduce, biography, view_count, follower_count, following_count) VALUES
    (1, UNHEX(REPLACE('65b87d26-9482-4984-843a-bee6efb3d9cd', '-', '')), '홍길동', 'esthete032@gmail.com', 'USER', 'MALE', '안녕하세요.', '저는 니콘 카메라를 즐겨씁니다!', 132, 102, 0),
    (2, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'test_user2', 'asefa1354@naver.com', 'USER', 'FEMALE', '반가워요.', '저는 코닥 카메라를 즐겨씁니다!', 224, 201, 123),
    (3, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'test_user3', 'riquer546@naver.com', 'USER', 'MALE', '행복해요.', '요즘은 필름 카메라에 빠졌네요..', 11231, 5042, 6034),
    (4, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'test_user4', 'fpdogsj44@naver.com', 'USER', 'FEMALE', '즐거워요.', '소통하실분?', 1241421, 100132, 1),
    (5, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'test_user5', 'sfas46587@naver.com', 'USER', 'MALE', '화가나요.', '으아아아아ㅏㅇㄱ', 15, 12, 14),
    (6, UNHEX(REPLACE('d843ab0d-3b90-4d78-9812-7a5f3c11d312', '-', '')), 'test_user6', 'dswe133@naver.com', 'USER', 'FEMALE', '집갈래요.', '자고싶어요 흑흑', 314, 414, 123),
    (7, UNHEX(REPLACE('f1a92b4d-8d08-4d02-9a9b-07c9979e9b24', '-', '')), 'test_user7', 'afgs2324@naver.com', 'USER', 'MALE', '배고파요.', '점메추 해주실 분?', 5, 6, 7),
    (8, UNHEX(REPLACE('b21b5a4c-96f8-4a3b-af6e-7bf50b065365', '-', '')), 'test_user8', 'dsg23521@naver.com', 'USER', 'MALE', '엄마 보고 싶다.', '흑흑', 1, 2, 3),
    (9, UNHEX(REPLACE('6b6590ac-003f-4c8b-82c3-0e2e24ce7cc8', '-', '')), 'test_user9', 'sdfhh32@naver.com', 'USER', 'FEMALE', '아빠 보고 싶다.', '흑흑', 11, 22, 33),
    (10, UNHEX(REPLACE('fda6f365-0a2d-4b48-ae6c-935f1f6b2f2a', '-', '')), 'test_user10', 'we214@naver.com', 'USER', 'MALE', '검정신발입니다.', '화이팅합시다!!', 3214, 6124, 7466)
    ON DUPLICATE KEY UPDATE users_uuid = users_uuid;

-- 디폴트 유저 프로필 URL 삽입
INSERT INTO profile_urls (profile_urls_id, users_id, img_url, s3_url) VALUES
    (1, 1, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (2, 2, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (3, 3, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (4, 4, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (5, 5, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (6, 6, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (7, 7, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (8, 8, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (9, 9, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png'),
    (10, 10, 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png', 'https://d1g6qszf7cmafu.cloudfront.net/default/profile.png')
    ON DUPLICATE KEY UPDATE profile_urls_id = profile_urls_id;

-- 디폴트 유저 태그 삽입
INSERT INTO users_tags (users_tags_id, users_id, tags_id)
VALUES
    (1, 1, 1),
    (2, 1, 3),
    (3, 1, 10),
    (4, 2, 2),
    (5, 2, 4),
    (6, 2, 9),
    (7, 3, 5),
    (8, 3, 8),
    (9, 3, 7),
    (10, 4, 6),
    (11, 4, 1),
    (12, 4, 9),
    (13, 5, 6),
    (14, 5, 2),
    (15, 5, 5),
    (16, 6, 10),
    (17, 6, 2),
    (18, 6, 3),
    (19, 7, 6),
    (20, 7, 7),
    (21, 7, 8),
    (22, 8, 11),
    (23, 8, 1),
    (24, 8, 3),
    (25, 9, 4),
    (26, 9, 7),
    (27, 9, 9),
    (28, 10, 2),
    (29, 10, 11),
    (30, 10, 5)
ON DUPLICATE KEY UPDATE users_tags_id = users_tags_id;

-- 디폴트 임시저장 전시 삽입
INSERT INTO temporary_exhibitions (temporary_exhibitions_id, temporary_exhibitions_uuid, users_id, thumbnail_url, title, description, created_at) VALUES
    (1, UNHEX(REPLACE('a5c6e3f7-8a91-4a54-bd3e-d0e77a8d4e7e', '-', '')), 1, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', '봄이다.', '봄이네요..', NOW()),
    (2, UNHEX(REPLACE('b4f9f0ea-5b71-4dbf-a2de-1fc82b86d86b', '-', '')), 1, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', '여름이다.', '덥네요..', NOW()),
    (3, UNHEX(REPLACE('c1e17a68-f30b-4fa1-9f6d-dde228bf9ff2', '-', '')), 1, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', '가을이다.', '좋네요..', NOW()),
    (4, UNHEX(REPLACE('d2f0cb21-7c9e-4a5b-950e-ea1ef2a3ad75', '-', '')), 1, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', '겨울이다.', '춥네요..', NOW())
    ON DUPLICATE KEY UPDATE temporary_exhibitions_uuid = temporary_exhibitions_uuid;


-- 디폴트 전시 삽입
INSERT INTO exhibitions (created_at, exhibitions_id, users_id, view_count, like_count, exhibitions_uuid, title, description, thumbnail_url) VALUES
    (NOW(), 1, 2, 203, 205, UNHEX(REPLACE('d8265394-573e-4d5e-baf0-8b75fe10896e', '-', '')), '겨울', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 2, 2, 152, 155, UNHEX(REPLACE('b35b9358-df1d-4d9c-b362-cb51e94e5e25', '-', '')), '겨울1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 3, 3, 313, 216, UNHEX(REPLACE('5f2e2f9d-0411-437e-91e5-7922a35b1044', '-', '')), '가을1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 4, 2, 22, 12, UNHEX(REPLACE('d61d5f71-f5bc-4ab3-92c8-62b1de207102', '-', '')), '가을2', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 5, 2, 15, 10, UNHEX(REPLACE('74148b2b-9f0e-4650-8e94-3ec6ccde34a6', '-', '')), '봄', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 6, 3, 1000, 562, UNHEX(REPLACE('f5f16b49-12ac-4745-aac5-4b1b269eabcb', '-', '')), '봄1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 7, 2, 123, 120, UNHEX(REPLACE('a3087e10-7da2-4760-a82d-d80c4397e0e1', '-', '')), '봄2', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 8, 1, 456, 423, UNHEX(REPLACE('35519784-ce74-443b-8665-d1c00cafa6f5', '-', '')), '태양', '태양노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 9, 2, 78, 55, UNHEX(REPLACE('31216ebf-27a5-4013-bedf-06642401f332', '-', '')), '구름', '구름노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/31216ebf-27a5-4013-bedf-06642401f332/exhibition-photos/98e80855-83ac-4912-8119-373c797924a6.jpg'),
    (NOW(), 10, 3, 41, 40, UNHEX(REPLACE('2c8221cf-ea0b-442e-a49e-cd02e4af3ca8', '-', '')), '바람', '바람노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/2c8221cf-ea0b-442e-a49e-cd02e4af3ca8/exhibition-photos/38584504-e55f-40de-afeb-7ae754efe347.jpg'),
    (NOW(), 11, 3, 22, 20, UNHEX(REPLACE('93dbf19e-6d22-4e3d-8a3b-c04b233bea38', '-', '')), '불', '붉은노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/93dbf19e-6d22-4e3d-8a3b-c04b233bea38/exhibition-photos/b9854f00-2b2f-4ce1-ab5e-3d2c83fd5568.jpg'),
    (NOW(), 12, 2, 123, 120, UNHEX(REPLACE('0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd', '-', '')), '꽃', '꽃가루', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd/exhibition-photos/86efe632-c2ff-430e-a936-32ad1de6d083.jpg'),
    (NOW(), 13, 5, 6, 4, UNHEX(REPLACE('61110976-0977-4393-8234-1ed9fd510cf3', '-', '')), '말', '말피', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/61110976-0977-4393-8234-1ed9fd510cf3/exhibition-photos/e0a53ff2-5032-4e3e-bc05-42e3013fa8b2.jpg'),
    (NOW(), 14, 2, 0, 0, UNHEX(REPLACE('7ea6b1a6-b6c0-4731-87e1-f4844cca01d3', '-', '')), '코', '코날비', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg'),
    (NOW(), 15, 4, 77, 50, UNHEX(REPLACE('6422e443-fedd-4f94-ae26-6e4fa9b58860', '-', '')), '입', '입마개', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg')
    ON DUPLICATE KEY UPDATE exhibitions_id = exhibitions_id;
    
    
-- 디폴트 전시 태그 삽입
INSERT INTO exhibitions_tags (exhibitions_tags_id, exhibitions_id, temporary_exhibitions_id, tags_id)
VALUES
    (1, 1, 1, 1),
    (2, 1, 1, 3),
    (3, 1, 1, 10),
    (4, 2, 1, 2),
    (5, 2, 1, 4),
    (6, 2, 1, 9),
    (7, 3, 1, 5),
    (8, 3, 2, 8),
    (9, 3, 2, 7),
    (10, 4, 2, 6),
    (11, 4, 2, 1),
    (12, 4, 2, 9),
    (13, 5, 3, 6),
    (14, 5, 3, 2),
    (15, 5, 3, 5),
    (16, 6, 3, 10),
    (17, 6, 4, 2),
    (18, 6, 4, 3),
    (19, 7, 4, 6),
    (20, 7, 4, 7),
    (21, 7, 4, 8)
    ON DUPLICATE KEY UPDATE exhibitions_tags_id = exhibitions_tags_id;


-- 디폴트 전시 위치 삽입
INSERT INTO exhibitions_locations (exhibitions_locations_id, exhibitions_id, temporary_exhibitions_id, longitude, latitude, state, city, town)
VALUES
    (1, 1, NULL, 127.003594, 37.55805, 'Seoul', 'Jung District', '173-6 Jangchung-dong 2(i)-ga'),
    (2, 2, NULL, 126.9985627, 37.5592871, 'Seoul', 'Jung District', '30 Pildong-ro 1-gil'),
    (3, 3, NULL, 127.006229, 37.561343, 'Seoul', 'Jung District', '56-77 Jangchung-dong 1(il)-gal'),
    (4, 4, NULL, 127.005911, 37.56148, 'Seoul', 'Jung District', '57-9 Jangchung-dong 1(il)-ga'),
    (5, 5, NULL, 127.010989, 37.551993, 'Seoul', 'Jung District', '372-85 Sindang-dong'),
    (6, 6, NULL, 126.995965, 37.560144, 'Seoul', 'Jung District', '16 Pildong-ro'),
    (7, 7, NULL, 127.1662, 37.601581, 'Gyeonggi-do', 'Namyangju-sit', '산1041-2 Jigeum-dong'),
    (8, 8, NULL, 127.766922, 35.907757, 'Jeollabuk-do', 'Muju Seolcheon-myeon', '808-3 Samgong-ri'),
    (13, 9, NULL, 126.767892, 36.451235, '서울특별시', '강북구', '미아동'),
    (14, 10, NULL, 126.451234, 36.111234, '서울특별시', '영등포구', '여의동'),
    (15, 11, NULL, 126.44565, 36.44446, '서울특별시', '마포구', '상암동'),
    (16, 12, NULL, 127.12411, 36.23232, '서울특별시', '성북구', '보문동'),
    (17, 13, NULL, 126.34111, 36.55568, '서울특별시', '성북구', '돈암동'),
    (18, 14, NULL, 126.77799, 36.23456, '서울특별시', '강북구', '수유동'),
    (19, 15, NULL, 127.456121, 36.656598, '서울특별시', '노원구', '은행사거리'),
    -- 임시 저장
    (9, NULL, 1, 25.4, 21.4, '서울특별시', '은평구', '갈현동'),
    (10, NULL, 2, 127.5, 13.2, '서울특별시', '용산구', '후암동'),
    (11, NULL, 3, 40.3, 67.8, '서울특별시', '서대문구', '홍제동'),
    (12, NULL, 4, 87.5, 11.5, '서울특별시', '종로구', '인사동')
ON DUPLICATE KEY UPDATE exhibitions_id = exhibitions_id;


-- 디폴트 사진 및 임시저장 전시 사진 삽입
                 INSERT INTO photos (photos_id, gray_scale, exhibitions_id, temporary_exhibitions_id, filters_uuid, photos_uuid)
                 VALUES
                     (1, 2.5, 1, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4a', '-', '')), UNHEX(REPLACE('e275b6d8-4cc2-4b38-9f89-426f7bf3f1ed', '-', ''))),
                     (2, 3.5, 1, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4b', '-', '')), UNHEX(REPLACE('a9db8cfe-f866-4d3d-b377-5ecf689ad91f', '-', ''))),
                     (3, 0.5, 2, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4c', '-', '')), UNHEX(REPLACE('12e332f8-0238-445d-9eb8-f1c2f8ac8a77', '-', ''))),
                     (4, 15.5, 3, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4d', '-', '')), UNHEX(REPLACE('047b6575-4025-4229-ab2e-49f4922b6354', '-', ''))),
                     (5, 31.5, 4, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4e', '-', '')), UNHEX(REPLACE('bc19fc1d-ca4d-4da5-92b8-4c0bfc71f4e5', '-', ''))),
                     (6, 14.5, 5, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e4f', '-', '')), UNHEX(REPLACE('f0b5158f-230e-4a44-a21f-693a671f100c', '-', ''))),
                     (7, 7.5, 6, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e5a', '-', '')), UNHEX(REPLACE('714f9974-a8df-46f8-b0bd-edb13174151b', '-', ''))),
                     (8, 6.5, 7, NULL, UNHEX(REPLACE('bc2ae5f7-b8cf-4468-baf5-6969c43d0e7a', '-', '')), UNHEX(REPLACE('02b52d52-b400-4bf1-8879-57f08a69cfd9', '-', ''))),
                     -- 임시저장
                     (9, -10.2, NULL, 1, UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d479', '-', '')), UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d480', '-', ''))),
                     (10, -10.2, NULL, 2, UNHEX(REPLACE('9c2c279b-35e4-4bda-b788-6bb5d99f6c91', '-', '')), UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d481', '-', ''))),
                     (11, -10.2, NULL, 3, UNHEX(REPLACE('ebee8bf8-87e7-41f6-8f60-d6e1ad2f2a22', '-', '')), UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d482', '-', ''))),
                     (12, -10.2, NULL, 4, UNHEX(REPLACE('4c566ef8-57e7-4397-91a1-dcfb7a058d6f', '-', '')), UNHEX(REPLACE('f47ac10b-58cc-4372-a567-0e02b2c3d483', '-', '')))
                 ON DUPLICATE KEY UPDATE photos_id = photos_id;


-- 디폴트 사진 및 임시저장 전시 사진 URL 삽입
INSERT INTO photos_urls (photos_urls_id, photos_id, img_url, s3_url)
VALUES
    (1, 1, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (2, 2, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/31216ebf-27a5-4013-bedf-06642401f332/exhibition-photos/98e80855-83ac-4912-8119-373c797924a6.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/31216ebf-27a5-4013-bedf-06642401f332/exhibition-photos/98e80855-83ac-4912-8119-373c797924a6.jpg'),
    (3, 3, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/2c8221cf-ea0b-442e-a49e-cd02e4af3ca8/exhibition-photos/38584504-e55f-40de-afeb-7ae754efe347.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/2c8221cf-ea0b-442e-a49e-cd02e4af3ca8/exhibition-photos/38584504-e55f-40de-afeb-7ae754efe347.jpg'),
    (4, 4, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/93dbf19e-6d22-4e3d-8a3b-c04b233bea38/exhibition-photos/b9854f00-2b2f-4ce1-ab5e-3d2c83fd5568.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/93dbf19e-6d22-4e3d-8a3b-c04b233bea38/exhibition-photos/b9854f00-2b2f-4ce1-ab5e-3d2c83fd5568.jpg'),
    (5, 5, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd/exhibition-photos/86efe632-c2ff-430e-a936-32ad1de6d083.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd/exhibition-photos/86efe632-c2ff-430e-a936-32ad1de6d083.jpg'),
    (6, 6, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/61110976-0977-4393-8234-1ed9fd510cf3/exhibition-photos/e0a53ff2-5032-4e3e-bc05-42e3013fa8b2.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/61110976-0977-4393-8234-1ed9fd510cf3/exhibition-photos/e0a53ff2-5032-4e3e-bc05-42e3013fa8b2.jpg'),
    (7, 7,  'https://d1g6qszf7cmafu.cloudfront.net/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg'),
    (8, 8, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg', 'https://esthete-bucket.s3.amazonaws.com/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg'),
    -- 임시저장
    (9, 9, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (10, 10, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1235.jpeg'),
    (11, 11, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1236.jpeg'),
    (12, 12, 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1237.jpeg')
    ON DUPLICATE KEY UPDATE photos_urls_id = photos_urls_id;


-- 디폴트 좋아요 삽입
INSERT INTO likes (likes_id, users_uuid, exhibitions_uuid)
VALUES
    (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('d8265394-573e-4d5e-baf0-8b75fe10896e', '-', ''))),
    (2, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('b35b9358-df1d-4d9c-b362-cb51e94e5e25', '-', ''))),
    (3, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('5f2e2f9d-0411-437e-91e5-7922a35b1044', '-', ''))),
    (4, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('d61d5f71-f5bc-4ab3-92c8-62b1de207102', '-', ''))),
    (5, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('74148b2b-9f0e-4650-8e94-3ec6ccde34a6', '-', ''))),
    (6, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('f5f16b49-12ac-4745-aac5-4b1b269eabcb', '-', ''))),
    (7, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('a3087e10-7da2-4760-a82d-d80c4397e0e1', '-', ''))),
    (8, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), UNHEX(REPLACE('35519784-ce74-443b-8665-d1c00cafa6f5', '-', '')))
    ON DUPLICATE KEY UPDATE likes_id = likes_id;