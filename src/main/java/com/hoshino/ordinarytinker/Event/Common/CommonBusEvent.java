package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Content.Entity.Hajimi;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerLivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonBusEvent {
    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event){
        event.put(OrdinaryTinkerLivingEntity.HAJIMI.get(), Hajimi.createAttributes().build());
    }
}
