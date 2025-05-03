package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.KeyBroad.KeyBinding;
import com.hoshino.ordinarytinker.Content.Entity.Renderer.HajimiRender;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerLivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class BusClientEvent {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinding.DIGGING_SPEED_KEY);
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(
                OrdinaryTinkerLivingEntity.HAJIMI.get(),
                HajimiRender::new
        );
    }
}
