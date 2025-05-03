package com.hoshino.ordinarytinker.Content.Util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownSystem {
    private static final ConcurrentHashMap<UUID, Long> cooldownMap = new ConcurrentHashMap<>();
    // 检查是否冷却完成（若完成则自动重置冷却）
    public static boolean checkAndResetCooldown(UUID id, long cooldownMillis) {
        long now = System.currentTimeMillis();
        Long result = cooldownMap.compute(id, (k, oldTime) ->
                (oldTime == null || now - oldTime >= cooldownMillis) ? now : oldTime
        );
        return result == now;
    }

    // 获取剩余冷却时间（毫秒）
    public static long getRemainingTime(UUID id, long cooldownMillis) {
        Long lastUsed = cooldownMap.get(id);
        if (lastUsed == null) return 0;
        long elapsed = System.currentTimeMillis() - lastUsed;
        return Math.max(cooldownMillis - elapsed, 0);
    }
}