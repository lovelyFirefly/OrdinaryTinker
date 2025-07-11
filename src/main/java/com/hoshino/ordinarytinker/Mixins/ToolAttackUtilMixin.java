package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.function.DoubleSupplier;

@Mixin(ToolAttackUtil.class)
public class ToolAttackUtilMixin {
    @Unique
    private static LivingEntity ordinarytinker$player;

    @ModifyVariable(
            method = "attackEntity(Lslimeknights/tconstruct/library/tools/nbt/IToolStackView;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/entity/Entity;Ljava/util/function/DoubleSupplier;ZLnet/minecraft/world/entity/EquipmentSlot;)Z",
            at = @At(value = "INVOKE", target = "Lslimeknights/tconstruct/library/tools/nbt/IToolStackView;getModifierList()Ljava/util/List;"),
            index = 13,
            remap = false
    )

    private static boolean a(boolean value) {
        return ModifierUtil.getModifierLevel(ordinarytinker$player.getMainHandItem(), OrdinaryTinkerModifier.covertStaticModifier.getId()) > 0;
    }

    @Inject(method = "attackEntity(Lslimeknights/tconstruct/library/tools/nbt/IToolStackView;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/entity/Entity;Ljava/util/function/DoubleSupplier;ZLnet/minecraft/world/entity/EquipmentSlot;)Z", at = @At("HEAD"), remap = false)
    private static void cache(IToolStackView tool, LivingEntity attackerLiving, InteractionHand hand, Entity targetEntity, DoubleSupplier cooldownFunction, boolean isExtraAttack, EquipmentSlot sourceSlot, CallbackInfoReturnable<Boolean> cir) {
        ordinarytinker$player = attackerLiving;
    }
}
