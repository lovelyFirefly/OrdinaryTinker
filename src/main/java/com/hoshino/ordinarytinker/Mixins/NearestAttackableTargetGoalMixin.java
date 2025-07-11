package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(NearestAttackableTargetGoal.class)
public abstract class NearestAttackableTargetGoalMixin<T extends LivingEntity> extends TargetGoal {
    @Shadow
    @Nullable
    protected LivingEntity target;

    public NearestAttackableTargetGoalMixin(Mob pMob, boolean pMustSee) {
        super(pMob, pMustSee);
    }

    @Inject(method = "canUse", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void setRange(CallbackInfoReturnable<Boolean> cir) {
        if (this.target != null && this.target instanceof Player player) {
            if (ModifierLevel.EquipHasModifierlevel(player, OrdinaryTinkerModifier.covertStaticModifier.getId())) {
                var distance = target.position().distanceTo(mob.position());
                if (distance > 3) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
