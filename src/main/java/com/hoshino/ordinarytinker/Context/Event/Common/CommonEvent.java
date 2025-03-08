package com.hoshino.ordinarytinker.Context.Event.Common;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonEvent {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void LivingHurt(LivingHurtEvent event){
    }
}
