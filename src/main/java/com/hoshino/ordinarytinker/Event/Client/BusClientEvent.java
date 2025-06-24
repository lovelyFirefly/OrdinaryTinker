package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.KeyBroad.KeyBinding;
import com.hoshino.ordinarytinker.Content.Client.Renderer.HugeArrowRenderer;
import com.hoshino.ordinarytinker.Content.Entity.HugeArrow;
import com.hoshino.ordinarytinker.Content.Entity.SpecialArrow;
import com.hoshino.ordinarytinker.Content.Particle.ParticleType.StarFallParticleProvider;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEntity;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerParticle;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BusClientEvent {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.DIGGING_SPEED_KEY);
    }

    @SubscribeEvent
    public static void registerRenderers(RegisterRenderers event) {
        event.registerEntityRenderer(
                OrdinaryTinkerEntity.HAJIMI.get(),
                context -> new CatRenderer(context) {
                    @Override
                    public @NotNull ResourceLocation getTextureLocation(@NotNull Cat pEntity) {
                        return new ResourceLocation("textures/entity/cat/tabby.png");
                    }
                }
        );
        event.registerEntityRenderer(
                OrdinaryTinkerEntity.special_arrow.get(),
                context -> new ArrowRenderer<>(context) {
                    @Override
                    public @NotNull ResourceLocation getTextureLocation(@NotNull SpecialArrow pEntity) {
                        return new ResourceLocation("textures/entity/cat/tabby.png");
                    }
                }
        );
        event.registerEntityRenderer(
                OrdinaryTinkerEntity.huge_arrow.get(),
                context -> new HugeArrowRenderer(context) {
                    @Override
                    public @NotNull ResourceLocation getTextureLocation(@NotNull HugeArrow pEntity) {
                        return new ResourceLocation("textures/entity/cat/tabby.png");
                    }
                }
        );
    }

    @SubscribeEvent
    public static void onParticleProviderRegister(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(OrdinaryTinkerParticle.STARFALL.get(), StarFallParticleProvider::new);
    }
}
