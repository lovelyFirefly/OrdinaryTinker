package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Event.CompletelyNewEvent.FluidConsumedEvent;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonLivingEvent {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void commonLivingAttack(LivingAttackEvent event){

    }
    @SubscribeEvent
    public static void costHealEvent(LivingHealEvent event){
        if(event.getEntity().hasEffect(OrdinaryTinkerEffect.mercurypoisoning.get())){
            event.setAmount(event.getAmount() * 0.5F);
        }
    }
    @SubscribeEvent
    public static void drinkMilk(PlayerInteractEvent event){
        if(event.getEntity().level().isClientSide())return;
        if(event.getItemStack().getItem()==Items.MILK_BUCKET){
            event.getEntity().heal(event.getEntity().getMaxHealth() * 0.24f);
        }
    }
    @SubscribeEvent
    public static void slurpUseEvent(FluidConsumedEvent event){
        if(event.getOriginalFluid().getFluid()== ForgeMod.MILK.get()){
            event.setConsumed(Math.round(event.getConsumed() * 0.1f));
            event.getPlayer().heal(event.getPlayer().getMaxHealth() * 0.23F);
        }
    }
}
