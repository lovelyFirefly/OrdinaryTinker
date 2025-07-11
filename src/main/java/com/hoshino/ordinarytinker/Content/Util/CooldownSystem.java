package com.hoshino.ordinarytinker.Content.Util;

import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownSystem {
    private static final ConcurrentHashMap<UUID, Long> cooldownMap = new ConcurrentHashMap<>();
    public static boolean checkAndResetCooldown(UUID id, long cooldownMillis) {
        long now = System.currentTimeMillis();
        Long result = cooldownMap.compute(id, (k, oldTime) ->
                (oldTime == null || now - oldTime >= cooldownMillis) ? now : oldTime
        );
        return result == now;
    }

    public static long getRemainingTime(UUID id, long cooldownMillis) {
        Long lastUsed = cooldownMap.get(id);
        if (lastUsed == null) return 0;
        long elapsed = System.currentTimeMillis() - lastUsed;
        return Math.max(cooldownMillis - elapsed, 0);
    }
}