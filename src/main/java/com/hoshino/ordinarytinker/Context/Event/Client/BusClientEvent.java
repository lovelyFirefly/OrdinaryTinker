package com.hoshino.ordinarytinker.Context.Event.Client;

import com.hoshino.ordinarytinker.Context.Client.KeyBroad.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class BusClientEvent {
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event){
        event.register(KeyBinding.DIGGING_SPEED_KEY);
    }
}
