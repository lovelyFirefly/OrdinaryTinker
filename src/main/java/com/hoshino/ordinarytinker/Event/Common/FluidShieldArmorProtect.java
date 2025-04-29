package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem.FluidShieldArmor;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class FluidShieldArmorProtect {
//    @SubscribeEvent()
//        public static void onModifyDamage(LivingHurtEvent event) {
//        if (event.getEntity() instanceof Player player) {
//            List<ItemStack> armors = player.getInventory().armor;
//            float totalModify = 0;
//            float totalReflect = 0;
//            int canModifyEquip=0;
//            boolean reflect = false;
//            for (ItemStack armor : armors) {
//                if (armor.getItem() instanceof FluidShieldArmor) {
//                    var fluidStack = ToolTankHelper.TANK_HELPER.getFluid(ToolStack.from(armor));
//                    float modify = FluidShieldArmor.getModifyScale(armor);
//                    if(fluidStack.getFluid()==Fluids.WATER){
//                        totalModify += modify;
//                        canModifyEquip++;
//                    }else if(fluidStack.getFluid() ==Fluids.LAVA){
//                        totalReflect+=modify;
//                        totalModify += modify;
//                        canModifyEquip++;
//                        reflect=true;
//                    }
//                }
//            }
//            float damageShouldPrevent = event.getAmount() * Math.min(totalModify, 1);
//            for (ItemStack armor : armors) {
//                if (armor.getItem() instanceof FluidShieldArmor) {
//                    var fluidStack = ToolTankHelper.TANK_HELPER.getFluid(ToolStack.from(armor));
//                    float eachCost=FluidShieldArmor.finalCost(armor);
//                    int finalCost=Math.round(eachCost * (damageShouldPrevent/canModifyEquip));
//                    if (fluidStack.getFluid() == Fluids.WATER||fluidStack.getFluid() == Fluids.LAVA) {
//                        fluidStack.shrink(finalCost);
//                        ToolTankHelper.TANK_HELPER.setFluid(ToolStack.from(armor),fluidStack);
//                    }
//                }
//            }
//            event.setAmount(Math.max(event.getAmount() - damageShouldPrevent,0));
//            if(event.getSource().getEntity() instanceof LivingEntity enemy &&reflect&&!event.getSource().is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)){
//                enemy.hurt(OTDamageTypes.source(player.level(),OTDamageTypes.SpecialReflect,player,player),totalReflect * damageShouldPrevent);
//                enemy.setRemainingFireTicks(Math.round(totalReflect * damageShouldPrevent * 20));
//            }
//        }
//    }
    @SubscribeEvent()
    public static void onModifyDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        List<ItemStack> armors = player.getInventory().armor;
        float totalModify = 0, totalReflect = 0;
        int canModifyEquip = 0;
        boolean reflect = false;
        // 第一遍遍历：计算总减伤和反射比例
        for (ItemStack armor : armors) {
            if (!(armor.getItem() instanceof FluidShieldArmor)) continue;
            var fluidStack = ToolTankHelper.TANK_HELPER.getFluid(ToolStack.from(armor));
            float modify = FluidShieldArmor.getModifyScale(armor);
            if (fluidStack.getFluid() == Fluids.WATER) {
                totalModify += modify;
                canModifyEquip++;
            } else if (fluidStack.getFluid() == Fluids.LAVA) {
                totalReflect += modify;
                totalModify += modify;
                canModifyEquip++;
                reflect = true;
            }
        }
        // 计算实际减伤量
        float damagePrevented = event.getAmount() * Math.min(totalModify, 1);
        event.setAmount(Math.max(event.getAmount() - damagePrevented, 0));
        // 第二遍遍历：消耗流体
        if (canModifyEquip > 0) {
            float finalCostBase = damagePrevented / canModifyEquip;
            armors.forEach(armor -> {
                if (!(armor.getItem() instanceof FluidShieldArmor)) return;
                var fluidStack = ToolTankHelper.TANK_HELPER.getFluid(ToolStack.from(armor));
                if (fluidStack.getFluid() != Fluids.WATER && fluidStack.getFluid() != Fluids.LAVA) return;
                int finalCost = Math.round(FluidShieldArmor.finalCost(armor) * finalCostBase);
                fluidStack.shrink(finalCost);
                ToolTankHelper.TANK_HELPER.setFluid(ToolStack.from(armor), fluidStack);
            });
        }
        // 反射伤害处理
        if (reflect && event.getSource().getEntity() instanceof LivingEntity enemy && !event.getSource().is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)) {
            enemy.hurt(OTDamageTypes.source(player.level(), OTDamageTypes.SpecialReflect, player, player),totalReflect * damagePrevented);
            enemy.setRemainingFireTicks(Math.round(totalReflect * damagePrevented * 20));
        }
    }
}
