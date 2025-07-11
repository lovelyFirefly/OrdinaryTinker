package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRenderLogic;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererUtil;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererEnum;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class HaloRender {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.CLIENT) {
            Player player = event.player;
            if (player.isSleeping()) return;
            Arrays.stream(HaloRendererEnum.values()).parallel().forEach(halo -> {
                int level = ModifierLevel.getAllSlotModifierlevel(player,halo.getModifierId());
                HaloRendererUtil.HALO_STATES.put(halo.getModifierId(), level > 0);
            });
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        float partialTick = event.getPartialTick();
        Arrays.stream(HaloRendererEnum.values())
                .filter(halo -> halo.checkCondition(player))
                .forEach(halo -> HaloRenderLogic.renderRotationHaloHorizontal(poseStack, player, partialTick, halo.getTexture()));
    }
}
