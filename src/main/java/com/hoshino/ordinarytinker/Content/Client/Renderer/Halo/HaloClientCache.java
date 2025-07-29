package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HaloClientCache {
    private static final Map<UUID, Map<ModifierId, Boolean>> CLIENT_PLAYER_HALO_STATES = new ConcurrentHashMap<>();

    public static boolean getHaloState(UUID playerUUID, ModifierId modifierId) {
        Map<ModifierId, Boolean> playerStates = CLIENT_PLAYER_HALO_STATES.get(playerUUID);
        return playerStates != null && playerStates.getOrDefault(modifierId, false);
    }

    public static void updatePlayerHaloStates(UUID playerUUID, Map<ModifierId, Boolean> newHaloStates) {
        CLIENT_PLAYER_HALO_STATES.computeIfAbsent(playerUUID, k -> new ConcurrentHashMap<>()).putAll(newHaloStates);
    }

    public static void clearPlayerHaloStates(UUID playerUUID) {
        CLIENT_PLAYER_HALO_STATES.remove(playerUUID);
    }

    public static void clearAllHaloStates() {
        CLIENT_PLAYER_HALO_STATES.clear();
    }
}
