package com.hoshino.ordinarytinker.Event.Server;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererEnum;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Network.OTChannel;
import com.hoshino.ordinarytinker.Network.Packet.HaloUpdatePacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID,value = Dist.DEDICATED_SERVER)
public class HaloUpdateOnServer {
    private static final Map<UUID, Map<ModifierId, Boolean>> SERVER_PLAYER_HALO_CACHE = new ConcurrentHashMap<>();
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            if (!(event.player instanceof ServerPlayer serverPlayer)) return;
            if (serverPlayer.tickCount % 20 == 0) {
                for (HaloRendererEnum halo : HaloRendererEnum.values()) {
                    boolean currentHaloState = ModifierLevel.getAllSlotModifierlevel(serverPlayer, halo.getModifierId()) > 0;
                    Map<ModifierId, Boolean> playerCache = SERVER_PLAYER_HALO_CACHE.computeIfAbsent(serverPlayer.getUUID(), k -> new ConcurrentHashMap<>());
                    boolean previousHaloState = playerCache.getOrDefault(halo.getModifierId(), false);
                    if (currentHaloState != previousHaloState) {
                        playerCache.put(halo.getModifierId(), currentHaloState);
                        OTChannel.sendToTrackingAndSelf(new HaloUpdatePacket(serverPlayer.getUUID(), halo.getModifierId(), currentHaloState), serverPlayer);
                    }
                }
            }
        }
    }
}
