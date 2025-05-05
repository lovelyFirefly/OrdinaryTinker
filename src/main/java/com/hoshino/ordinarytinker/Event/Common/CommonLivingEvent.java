package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Content.Util.EquipmentHelper;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Event.CompletelyNewEvent.FluidConsumedEvent;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

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
    @SubscribeEvent
    public static void preventDeath(LivingDeathEvent event){
        ResourceLocation SOUL=OrdinaryTinker.getResource("soul");
        if(event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY))return;
        if(event.getEntity()instanceof Player player&& ModifierLevel.getAllSlotModifierlevel(player, OrdinaryTinkerModifier.dementorsStaticModifier.getId())>0){
            for(ItemStack stack: EquipmentHelper.getEquipmentList(player)){
                IToolStackView view= ToolStack.from(stack);
                int soulAmount=view.getPersistentData().getInt(SOUL);
                if(soulAmount>10){
                    view.getPersistentData().putInt(SOUL,soulAmount - 10);
                    event.setCanceled(true);
                    event.getEntity().setHealth(10);
                    return;
                }
            }
        }
    }
}
