package com.hoshino.ordinarytinker.Context.Event.Common;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonEvent {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void LivingHurt(LivingHurtEvent event){
        if(event.getSource().getEntity() instanceof Player player){
            int a=player.experienceLevel;
        }
    }
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void PlayerHurtEvent(PlayerEvent.BreakSpeed event){
      if(event.getOriginalSpeed()>0){
          event.setNewSpeed(10000);
      }
    }
}
