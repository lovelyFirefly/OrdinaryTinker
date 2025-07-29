package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloClientCache;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRegistry;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class HaloRender {
    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        float partialTick = event.getPartialTick();
        for (HaloRendererUtil halo : HaloRegistry.getAllHalos()) {
            if (HaloClientCache.getHaloState(player.getUUID(), halo.getModifierId())) {
                halo.doRender(poseStack, player, partialTick);
            }
        }
    }
}
