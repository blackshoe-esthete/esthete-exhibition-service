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
INSERT INTO users (users_id, users_uuid, nickname, email, role, gender, introduce, biography, view_count, follower_count, following_count) VALUES
    (1, UNHEX(REPLACE('23e7b2b4-c1ac-4591-bb7f-c6706daf22aa', '-', '')), 'test_user', 'hsh111366@naver.com', 'USER', 'MALE', '안녕하세요.', '저는 니콘 카메라를 즐겨씁니다!', 132, 102, 0),
    (2, UNHEX(REPLACE('550e8400-e29b-41d4-a716-446655440000', '-', '')), 'test_user2', 'asefa1354@naver.com', 'USER', 'FEMALE', '반가워요.', '저는 코닥 카메라를 즐겨씁니다!', 224, 201, 123),
    (3, UNHEX(REPLACE('4b55df30-7a87-49b2-bd56-e0f5210a9a5d', '-', '')), 'test_user3', 'riquer546@naver.com', 'USER', 'MALE', '행복해요.', '요즘은 필름 카메라에 빠졌네요..', 11231, 5042, 6034),
    (4, UNHEX(REPLACE('86a93e29-0f46-4a65-9c49-7fbf7c13e9f2', '-', '')), 'test_user4', 'fpdogsj44@naver.com', 'USER', 'FEMALE', '즐거워요.', '소통하실분?', 1241421, 100132, 1),
    (5, UNHEX(REPLACE('4d4be043-5d57-45eb-a3fb-dc48e5e452b0', '-', '')), 'test_user5', 'sfas46587@naver.com', 'USER', 'MALE', '화가나요.', 'Skrrr', 15, 12, 14)
    ON DUPLICATE KEY UPDATE users_uuid = users_uuid;

-- 디폴트 유저 프로필 URL 삽입
INSERT INTO profile_urls (profile_urls_id, users_id, img_url, s3_url) VALUES
    (1, 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (2, 2, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (3, 3, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (4, 4, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (5, 5, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg')
    ON DUPLICATE KEY UPDATE profile_urls_id = profile_urls_id;


-- 디폴트 임시저장 전시 삽입
INSERT INTO temporary_exhibitions (temporary_exhibitions_uuid, users_id, thumbnail_url, title, description, created_at) VALUES
    (UNHEX(REPLACE('a5c6e3f7-8a91-4a54-bd3e-d0e77a8d4e7e', '-', '')), 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', '봄이다.', '봄이네요..', NOW()),
    (UNHEX(REPLACE('b4f9f0ea-5b71-4dbf-a2de-1fc82b86d86b', '-', '')), 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf5678.jpeg', '여름이다.', '덥네요..', NOW()),
    (UNHEX(REPLACE('c1e17a68-f30b-4fa1-9f6d-dde228bf9ff2', '-', '')), 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf9012.jpeg', '가을이다.', '좋네요..', NOW()),
    (UNHEX(REPLACE('d2f0cb21-7c9e-4a5b-950e-ea1ef2a3ad75', '-', '')), 1, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf9012.jpeg', '겨울이다.', '춥네요..', NOW())
    ON DUPLICATE KEY UPDATE temporary_exhibitions_uuid = temporary_exhibitions_uuid;


-- 디폴트 전시 삽입
INSERT INTO exhibitions (created_at, exhibitions_id, users_id, view_count, like_count, exhibitions_uuid, title, description, thumbnail_url) VALUES
    (NOW(), 1, 1, 203, 205, UNHEX(REPLACE('d8265394-573e-4d5e-baf0-8b75fe10896e', '-', '')), '겨울', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 2, 2, 152, 155, UNHEX(REPLACE('b35b9358-df1d-4d9c-b362-cb51e94e5e25', '-', '')), '겨울1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 3, 3, 313, 216, UNHEX(REPLACE('5f2e2f9d-0411-437e-91e5-7922a35b1044', '-', '')), '가을1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 4, 2, 22, 12, UNHEX(REPLACE('d61d5f71-f5bc-4ab3-92c8-62b1de207102', '-', '')), '가을2', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 5, 2, 15, 10, UNHEX(REPLACE('74148b2b-9f0e-4650-8e94-3ec6ccde34a6', '-', '')), '봄', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 6, 3, 1000, 562, UNHEX(REPLACE('f5f16b49-12ac-4745-aac5-4b1b269eabcb', '-', '')), '봄1', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 7, 2, 123, 120, UNHEX(REPLACE('a3087e10-7da2-4760-a82d-d80c4397e0e1', '-', '')), '봄2', '혼자여행', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 8, 1, 456, 423, UNHEX(REPLACE('35519784-ce74-443b-8665-d1c00cafa6f5', '-', '')), '태양', '태양노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/35519784-ce74-443b-8665-d1c00cafa6f5/exhibition-photos/7b75cde4-ba6b-405f-9a36-eafd2e7e163e.jpg'),
    (NOW(), 9, 1, 78, 55, UNHEX(REPLACE('31216ebf-27a5-4013-bedf-06642401f332', '-', '')), '구름', '구름노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/31216ebf-27a5-4013-bedf-06642401f332/exhibition-photos/98e80855-83ac-4912-8119-373c797924a6.jpg'),
    (NOW(), 10, 1, 41, 40, UNHEX(REPLACE('2c8221cf-ea0b-442e-a49e-cd02e4af3ca8', '-', '')), '바람', '바람노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/2c8221cf-ea0b-442e-a49e-cd02e4af3ca8/exhibition-photos/38584504-e55f-40de-afeb-7ae754efe347.jpg'),
    (NOW(), 11, 1, 22, 20, UNHEX(REPLACE('93dbf19e-6d22-4e3d-8a3b-c04b233bea38', '-', '')), '불', '붉은노을', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/93dbf19e-6d22-4e3d-8a3b-c04b233bea38/exhibition-photos/b9854f00-2b2f-4ce1-ab5e-3d2c83fd5568.jpg'),
    (NOW(), 12, 1, 123, 120, UNHEX(REPLACE('0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd', '-', '')), '꽃', '꽃가루', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/0e7cb02a-0750-4f4b-b3ad-bb1aaf5577bd/exhibition-photos/86efe632-c2ff-430e-a936-32ad1de6d083.jpg'),
    (NOW(), 13, 1, 6, 4, UNHEX(REPLACE('61110976-0977-4393-8234-1ed9fd510cf3', '-', '')), '말', '말피', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/61110976-0977-4393-8234-1ed9fd510cf3/exhibition-photos/e0a53ff2-5032-4e3e-bc05-42e3013fa8b2.jpg'),
    (NOW(), 14, 1, 0, 0, UNHEX(REPLACE('7ea6b1a6-b6c0-4731-87e1-f4844cca01d3', '-', '')), '코', '코날비', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/7ea6b1a6-b6c0-4731-87e1-f4844cca01d3/exhibition-photos/9d19ca4a-4372-40fb-9b67-733df0e4a9bd.jpg'),
    (NOW(), 15, 1, 77, 50, UNHEX(REPLACE('6422e443-fedd-4f94-ae26-6e4fa9b58860', '-', '')), '입', '입마개', 'https://d1g6qszf7cmafu.cloudfront.net/exhibition/6422e443-fedd-4f94-ae26-6e4fa9b58860/exhibition-photos/b342c93a-02fb-4ef0-a12c-f2949501e6fd.jpg')
    ON DUPLICATE KEY UPDATE exhibitions_id = exhibitions_id;
    
    
-- 디폴트 전시 태그 삽입
INSERT INTO exhibitions_tags (exhibitions_tags_id, exhibitions_id, temporary_exhibitions_id, tags_id)
VALUES
    (1, 1, 1, 1),
    (2, 1, 1, 3),
    (3, 1, 1, 13),
    (4, 2, 1, 2),
    (5, 2, 1, 4),
    (6, 2, 1, 12),
    (7, 3, 1, 5),
    (8, 3, 2, 8),
    (9, 3, 2, 25),
    (10, 4, 2, 24),
    (11, 4, 2, 11),
    (12, 4, 2, 9),
    (13, 5, 3, 15),
    (14, 5, 3, 2),
    (15, 5, 3, 5),
    (16, 6, 3, 21),
    (17, 6, 4, 20),
    (18, 6, 4, 10),
    (19, 7, 4, 6),
    (20, 7, 4, 7),
    (21, 7, 4, 14)
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
    (9, 9, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1234.jpeg'),
    (10, 10, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1235.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1235.jpeg'),
    (11, 11, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1236.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1236.jpeg'),
    (12, 12, 'https://d30asln0ue7bf5.cloudfront.net/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1237.jpeg', 'https://blackshoe-esthete-s3/user/23e7b2b4-c1ac-4591-bb7f-c6706daf22aa/exhibition/c9189f9b-1e78-4117-a0e2-b9ca8ab27cf8/photo/asdf1237.jpeg')
    ON DUPLICATE KEY UPDATE photos_urls_id = photos_urls_id;