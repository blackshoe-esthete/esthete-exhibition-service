package com.blackshoe.esthete.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class RecommendationRepository {
    private final RedisTemplate<String, Long> redisTemplate;

    // 유저 ID에 대한 전시 ID를 Redis 리스트에 저장하는 메서드
    public void save(Long userId, Long exhibitionId) {
        ListOperations<String, Long> listOperations = redisTemplate.opsForList();
        listOperations.rightPush("userId:" + userId, exhibitionId);
    }

    // 특정 유저 ID에 대한 모든 전시 ID를 가져오는 메서드
    public List<Long> findAllByUserId(Long userId) {
        ListOperations<String, Long> listOperations = redisTemplate.opsForList();
        return listOperations.range("userId:" + userId, 0, -1);
    }

    // Redis에 있는 모든 키를 백업하는 메서드
    public void backupData() {
        Set<String> keys = redisTemplate.keys("userId:*");
        if (keys != null) {
            keys.forEach(key -> {
                List<Long> values = redisTemplate.opsForList().range(key, 0, -1);
                if (values != null && !values.isEmpty()) {
                    redisTemplate.opsForList().rightPushAll("backup:" + key, values);
                }
            });
        }
    }

    // Redis에 있는 모든 키를 삭제하는 메서드
    public void clearData() {
        Set<String> keys = redisTemplate.keys("userId:*");
        if (keys != null) {
            keys.forEach(redisTemplate::delete);
        }
    }

    // 백업 데이터를 복원하는 메서드
    public void restoreData() {
        Set<String> backupKeys = redisTemplate.keys("backup:userId:*");
        if (backupKeys != null) {
            backupKeys.forEach(backupKey -> {
                String originalKey = backupKey.replace("backup:", "");
                List<Long> values = redisTemplate.opsForList().range(backupKey, 0, -1);
                if (values != null && !values.isEmpty()) {
                    redisTemplate.opsForList().rightPushAll(originalKey, values);
                }
            });
        }
    }

    // 백업 데이터를 삭제하는 메서드
    public void deleteBackupData() {
        Set<String> backupKeys = redisTemplate.keys("backup:userId:*");
        if (backupKeys != null) {
            backupKeys.forEach(redisTemplate::delete);
        }
    }
}