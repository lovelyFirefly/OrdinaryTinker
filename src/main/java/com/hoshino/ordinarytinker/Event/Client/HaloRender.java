package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRenderLogic;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererUtil;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererEnum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import java.util.Arrays;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class HaloRender {
    // 统一事件处理
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.CLIENT) {
            Player player = event.player;
            if (player.isSleeping()) return;
            Arrays.stream(HaloRendererEnum.values()).parallel().forEach(strategy -> {
                int level = ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.HEAD), strategy.getModifierId());
                HaloRendererUtil.HALO_STATES.put(strategy.getModifierId(), level > 0);
            });
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        PoseStack poseStack = event.getPoseStack();
        float partialTick = event.getPartialTick();
        Arrays.stream(HaloRendererEnum.values())
                .filter(strategy -> strategy.checkCondition(player))
                .forEach(strategy -> HaloRenderLogic.renderCompleteDynamicHaloHorizontal(poseStack, player, partialTick, strategy.getTexture()));
    }
}
