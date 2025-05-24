package com.hoshino.ordinarytinker.Event.Client;

import com.google.gson.JsonParseException;
import com.hoshino.ordinarytinker.Content.Client.KeyBroad.KeyBinding;
import com.hoshino.ordinarytinker.Content.Entity.SpecialArrow;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerLivingEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

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
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
    }
//        @SubscribeEvent
//    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
//        Set<String> skins = event.getSkins();
//        for (String skinName : skins) {
//            PlayerRenderer playerRenderer = event.getSkin(skinName);
//            if (playerRenderer != null) {
//                playerRenderer.addLayer(new SoulgeRendererLayer(playerRenderer, event.getContext().getItemInHandRenderer()
//                ));
//            }
//        }
//    }
}
