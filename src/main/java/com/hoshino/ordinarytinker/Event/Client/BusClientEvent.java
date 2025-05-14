package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.KeyBroad.KeyBinding;
import com.hoshino.ordinarytinker.Content.Client.Renderer.Layer.SoulgeRendererLayer;
import com.hoshino.ordinarytinker.Content.Entity.SpecialArrow;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerLivingEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class BusClientEvent {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinding.DIGGING_SPEED_KEY);
    }
    @SubscribeEvent
    public static void registerRenderers(RegisterRenderers event){
        event.registerEntityRenderer(
                OrdinaryTinkerLivingEntity.HAJIMI.get(),
                context -> new CatRenderer(context){
                    @Override
                    public @NotNull ResourceLocation getTextureLocation(@NotNull Cat pEntity) {
                        return new ResourceLocation("textures/entity/cat/tabby.png");
                    }
                }
        );
        event.registerEntityRenderer(
                OrdinaryTinkerLivingEntity.special_arrow.get(),
                context-> new ArrowRenderer<>(context) {
                    @Override
                    public @NotNull ResourceLocation getTextureLocation(@NotNull SpecialArrow pEntity) {
                        return new ResourceLocation("textures/entity/cat/tabby.png");
                    }
                }
        );
    }
//    @SubscribeEvent
//    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
//        EntityRendererProvider.Context context=event.getContext();
//        var playerRender=new PlayerRenderer(context,false);
//        var a= event.getRenderer(EntityType.PLAYER);
//        event.getContext()
//    }
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        Set<String> skins = event.getSkins();
        for (String skinName : skins) {
            PlayerRenderer playerRenderer = event.getSkin(skinName);
            if (playerRenderer != null) {
                playerRenderer.addLayer(new SoulgeRendererLayer(playerRenderer, event.getContext().getItemInHandRenderer()
                ));
            }
        }
    }
}
