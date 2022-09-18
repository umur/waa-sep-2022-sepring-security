package edu.miu.lab5springsecurity.repository;

import edu.miu.lab6springsecurity.entity.redis.VulgarityUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VulgarityUsageRepo {
    public static final String HASH_KEY = "VulgarityUsage";
    private static Integer idCounter = 0;
    @Autowired
    private RedisTemplate<String, VulgarityUsage> redisTemplate;

    public VulgarityUsage save(VulgarityUsage vulgarityUsage) {
        vulgarityUsage.setId(++idCounter);
        redisTemplate.opsForHash().put(HASH_KEY, vulgarityUsage.getId(), vulgarityUsage);
        return vulgarityUsage;
    }

    public List<VulgarityUsage> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY).stream().map(o -> (VulgarityUsage)o).collect(Collectors.toList());
    }

    public List<VulgarityUsage> findByUserId(int userId) {
        var usage = findAll();
        return usage.stream().filter(u -> u.getUserId() == userId).collect(Collectors.toList());
    }

    public void deleteVulgarityUsage(int id){
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }

}
