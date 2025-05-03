package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRenderLogic;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Handler.ClientAzusaHaloHandler;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Handler.ClientHoshinoHaloHandler;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Handler.ClientReisaHaloHandler;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID,value = Dist.CLIENT)
public class HaloRender {
    private static final ResourceLocation HoshinoHalo= OrdinaryTinker.getResource("textures/halo/hoshino.png");
    private static final ResourceLocation ReisaHalo= OrdinaryTinker.getResource("textures/halo/reisa.png");
    private static final ResourceLocation AzusaHalo= OrdinaryTinker.getResource("textures/halo/azusa.png");

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.CLIENT) {
            Player player=event.player;
            if(player.isSleeping())return;
            ClientHoshinoHaloHandler.setHasHalo(ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.Armorcoating.getId()) > 0);
            ClientAzusaHaloHandler.setHasHalo(ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.Crcs.getId()) > 0);
            ClientReisaHaloHandler.setHasHalo(ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.Loyal.getId()) > 0);
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        if (ClientHoshinoHaloHandler.shouldRenderHalo(player)) {
            HaloRenderLogic.renderCompleteDynamicHaloHorizontal(event.getPoseStack(), player, event.getPartialTick(),HoshinoHalo);
        }
        if (ClientAzusaHaloHandler.shouldRenderHalo(player)) {
            HaloRenderLogic.renderCompleteDynamicHaloHorizontal(event.getPoseStack(), player, event.getPartialTick(),AzusaHalo);
        }
        if (ClientReisaHaloHandler.shouldRenderHalo(player)) {
            HaloRenderLogic.renderCompleteDynamicHaloHorizontal(event.getPoseStack(), player, event.getPartialTick(),ReisaHalo);
        }
    }
}
