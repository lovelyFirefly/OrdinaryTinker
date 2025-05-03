package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonLivingEvent {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void commonLivingHurt(LivingHurtEvent event){
    }
    @SubscribeEvent
    public static void costHealEvent(LivingHealEvent event){
        if(event.getEntity().hasEffect(OrdinaryTinkerEffect.mercurypoisoning.get())){
            event.setAmount(event.getAmount() * 0.5F);
        }
    }

}
