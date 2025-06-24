package com.hoshino.ordinarytinker.Event.Common;

import com.c2h6s.etstlib.event.CompletelyNewEvent.FluidConsumedEvent;
import com.hoshino.ordinarytinker.Content.Util.EquipmentHelper;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Event.CompletelyNewEvent.AddDamageTag;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tables.TinkerTables;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class CommonLivingEvent {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void commonLivingAttack(LivingAttackEvent event) {
        if(event.getSource() instanceof AddDamageTag tag){
            tag.ordinarytinker$addDamageTag(a->a.add(DamageTypeTags.IS_FIRE));
            if(event.getSource().is(DamageTypeTags.IS_FIRE)){
                if(event.getSource().getEntity()!=null){
                    event.getSource().getEntity().sendSystemMessage(Component.literal("是火焰伤害"));
                }
            }
        }
    }
    @SubscribeEvent
    public static void commonLivingHurt(LivingHurtEvent event){
        if(event.getSource().is(DamageTypeTags.IS_PROJECTILE)){
            event.getEntity().sendSystemMessage(Component.literal("是弹射物伤害"));
        }
    }
    @SubscribeEvent
    public static void criticalHitEvent(CriticalHitEvent event){

    }

    @SubscribeEvent
    public static void costHealEvent(LivingHealEvent event) {
        var entity=event.getEntity();
        if (entity.hasEffect(OrdinaryTinkerEffect.mercurypoisoning.get())) {
            event.setAmount(event.getAmount() * 0.5F);
        }
        int layer=entity.getPersistentData().getInt("natsuheal");
        if(layer>0){
            event.setAmount(event.getAmount() * (1+ 0.138f * layer ));
        }
    }

    @SubscribeEvent
    public static void drinkMilk(LivingEntityUseItemEvent.Finish event) {
        var entity=event.getEntity();
        if (entity.level().isClientSide()) return;
        if (event.getItem().getItem() == Items.MILK_BUCKET && ModifierLevel.getHeadModifierlevel(entity, OrdinaryTinkerModifier.natsuHaloStaticModifier.getId()) > 0) {
            entity.heal(entity.getMaxHealth() * 0.24f);
            natsuLayer(entity);
        }
    }
    @SubscribeEvent
    public static void usePotion(LivingEntityUseItemEvent.Finish event) {
        var entity=event.getEntity();
        if (entity.level().isClientSide()||!(entity instanceof Player player))return;
        var item=event.getItem();
        if(item.getItem() instanceof PotionItem){
            player.addItem(item);
        }
    }
    @SubscribeEvent
    public static void craftBack(PlayerEvent.ItemCraftedEvent event) {
        var entity=event.getEntity();
        if (entity.level().isClientSide())return;
        var item=event.getCrafting();
        if(item.getItem()== TinkerTables.pattern.get()&&ModifierLevel.EquipHasModifierlevel(entity,OrdinaryTinkerModifier.flashOfInspirationStaticModifier.getId())){
            ItemStack stack=new ItemStack(item.getItem(), Math.round(item.getCount() * 0.5f));
            entity.addItem(stack);
        }
    }
    @SubscribeEvent
    public static void onBack(PlayerEvent.ItemCraftedEvent event) {
        var item=event.getCrafting();
        var player=event.getEntity();
        ICondition.IContext conditionContext = ICondition.IContext.EMPTY;
        RecipeManager manager=new RecipeManager(conditionContext);
        var a= manager.getRecipes();
        for(Recipe<?>recipe:a){
            var resultItem=recipe.getResultItem(player.level().registryAccess());
            if(resultItem.getItem()==item.getItem()){
                var input=recipe.getIngredients();
            }
        }
    }
    private static void natsuLayer(LivingEntity entity){
        int layer=entity.getPersistentData().getInt("natsuheal");
        if(layer<3){
            entity.getPersistentData().putInt("natsuheal",layer+1);
        }
    }

    @SubscribeEvent
    public static void slurpUseEvent(FluidConsumedEvent event) {
        if (event.getOriginalFluid().getFluid() == ForgeMod.MILK.get()) {
            event.setConsumed(Math.round(event.getConsumed() * 0.1f));
            event.getPlayer().heal(event.getPlayer().getMaxHealth() * 0.23F);
            natsuLayer(event.getPlayer());
        }
    }
    @SubscribeEvent
    public static void conservePotion(FluidConsumedEvent event) {
        int modifierLevel=ModifierLevel.getTotalArmorModifierlevel(event.getPlayer(),OrdinaryTinkerModifier.potionMasterStaticModifier.getId());
        if(modifierLevel==0)return;
        if (event.getOriginalFluid().getFluid() == TinkerFluids.potion.get()) {
            boolean shouldSave=event.getPlayer().level().random.nextInt(100) * modifierLevel>8;
            if(shouldSave){
                event.setConsumed(0);
            }
        }
    }
    @SubscribeEvent
    public static void extensionMobEffectEvent(MobEffectEvent.Applicable event) {
        //局部变量获取
        var entity=event.getEntity();
        int modifierLevel=ModifierLevel.getTotalArmorModifierlevel(entity,OrdinaryTinkerModifier.potionMasterStaticModifier.getId());
        var instance=event.getEffectInstance();
        var effect=instance.getEffect();
        //排除不能被牛奶解除的药水效果
        if(instance.getEffect() instanceof NoMilkEffect)return;
        //排除无限时长的药水效果,排除瞬时药水效果
        if(instance.isInfiniteDuration()||effect.isInstantenous())return;
        Component.literal("aaa").withStyle(style -> style.withColor(0x654145));
        //摇数字看看是否应该延长
        boolean shouldAdd=entity.level().random.nextInt(11)==5;
        if(entity instanceof Player&&modifierLevel>0){
            //应用这个Int2IntFunction函数
            instance.duration= instance.mapDuration(duration-> Math.round(duration * (1-0.1f * modifierLevel)));
            if(shouldAdd&&modifierLevel>1&&instance.isInfiniteDuration()){
                instance.mapDuration(duration->{
                    instance.amplifier=instance.amplifier+1;
                    return Math.round(duration * (1+0.3f));
                });
            }
        }
    }
    @SubscribeEvent
    public static void preventDeath(LivingDeathEvent event) {
        ResourceLocation SOUL = OrdinaryTinker.getResource("soul");
        if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
        if (event.getEntity() instanceof Player player && ModifierLevel.getAllSlotModifierlevel(player, OrdinaryTinkerModifier.dementorsStaticModifier.getId()) > 0) {
            for (ItemStack stack : EquipmentHelper.getEquipmentList(player)) {
                IToolStackView view = ToolStack.from(stack);
                int soulAmount = view.getPersistentData().getInt(SOUL);
                if (soulAmount > 10) {
                    view.getPersistentData().putInt(SOUL, soulAmount - 10);
                    event.setCanceled(true);
                    event.getEntity().setHealth(10);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void scareTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            var tick = player.getPersistentData().getInt("fearfield");
            player.getPersistentData().putInt("fearfield", tick - 1);
        }
    }

    @SubscribeEvent
    public static void afterKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player&&ModifierLevel.EquipHasModifierlevel(player,OrdinaryTinkerModifier.uncannyValleyStaticModifier.getId())) {
            player.getPersistentData().putInt("fearfield", 100);
        }
    }
}
