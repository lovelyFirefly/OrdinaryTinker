package com.hoshino.ordinarytinker.Event.Server;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRegistry;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererUtil;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Network.OTChannel;
import com.hoshino.ordinarytinker.Network.Packet.HaloUpdatePacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class HaloUpdateOnServer {
    private static final Map<UUID, Map<ModifierId, Boolean>> SERVER_PLAYER_HALO_CACHE = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            if (!(event.player instanceof ServerPlayer serverPlayer)) return;
            if (serverPlayer.tickCount % 20 == 0) {
                Map<ModifierId, Boolean> haloStatesToSync = new ConcurrentHashMap<>();
                Map<ModifierId, Boolean> playerCache = SERVER_PLAYER_HALO_CACHE.computeIfAbsent(serverPlayer.getUUID(), k -> new ConcurrentHashMap<>());
                for (HaloRendererUtil halo : HaloRegistry.getAllHalos()) {
                    boolean currentHaloState = ModifierLevel.getAllSlotModifierlevel(serverPlayer, halo.getModifierId()) > 0;
                    boolean previousHaloState = playerCache.getOrDefault(halo.getModifierId(), false);
                    if (currentHaloState != previousHaloState) {
                        playerCache.put(halo.getModifierId(), currentHaloState);
                        haloStatesToSync.put(halo.getModifierId(), currentHaloState);
                    }
                }
                if (!haloStatesToSync.isEmpty()) {
                    OTChannel.sendToTrackingAndSelf(new HaloUpdatePacket(serverPlayer.getUUID(), haloStatesToSync), serverPlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        SERVER_PLAYER_HALO_CACHE.remove(event.getEntity().getUUID());
    }
}
