package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public interface HaloRendererUtil {
    Map<UUID, Map<ModifierId, Boolean>> PLAYER_HALO_STATES = new ConcurrentHashMap<>();

    static void setPlayerHaloState(UUID playerUUID, ModifierId modifierId, boolean isEnabled) {
        PLAYER_HALO_STATES.computeIfAbsent(playerUUID, k -> new ConcurrentHashMap<>()).put(modifierId, isEnabled);
    }

    static void clearAllHaloStates() {
        PLAYER_HALO_STATES.clear();
    }

    ResourceLocation getTexture();

    ModifierId getModifierId();

    default boolean checkCondition(Player player) {
        Map<ModifierId, Boolean> playerHalos = PLAYER_HALO_STATES.get(player.getUUID());
        boolean hasHaloEnabled = playerHalos != null && playerHalos.getOrDefault(getModifierId(), false);
        return hasHaloEnabled && !player.isInvisible();
    }
}
