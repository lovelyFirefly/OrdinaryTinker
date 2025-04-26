package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonEvent {
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
