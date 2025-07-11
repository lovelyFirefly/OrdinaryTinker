package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface HaloRendererUtil {
    Map<ModifierId, Boolean> HALO_STATES = new ConcurrentHashMap<>();

    ResourceLocation getTexture();

    default boolean checkCondition(Player player) {
        return HALO_STATES.getOrDefault(getModifierId(), false) && !player.isInvisible();
    }

    ModifierId getModifierId();
}
