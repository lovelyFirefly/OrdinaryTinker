package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow
    protected FoodData foodData;
    @Shadow
    @Final
    private Abilities abilities;

    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "canEat", at = @At("HEAD"), cancellable = true)
    private void shouldEat(boolean pCanAlwaysEat, CallbackInfoReturnable<Boolean> cir) {
        int level = ModifierLevel.getTotalArmorModifierlevel(this, OrdinaryTinkerModifier.iceBloodStaticModifier.getId());
        if (level > 0) {
            boolean canEat = level > 3 || this.foodData.getFoodLevel() < 15 * level + 20 || this.abilities.invulnerable || pCanAlwaysEat || this.foodData.needsFood();
            cir.setReturnValue(canEat);
        }
    }
//    @Inject(method = "getCurrentItemAttackStrengthDelay",at = @At("HEAD"), cancellable = true)
//    private void getAttackStrength(CallbackInfoReturnable<Float> cir){
//        cir.setReturnValue(1f);
//    }
}
