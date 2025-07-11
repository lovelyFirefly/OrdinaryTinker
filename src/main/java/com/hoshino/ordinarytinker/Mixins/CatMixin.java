package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cat.CatAvoidEntityGoal.class)
public abstract class CatMixin<T extends LivingEntity> extends AvoidEntityGoal<T> {
    @Shadow
    @Final
    private Cat cat;

    public CatMixin(PathfinderMob pMob, Class<T> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
        super(pMob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier);
    }

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir) {
        Cat cat = this.cat;
        var potentialTarget = cat.level().getNearestPlayer(mob, 20);
        if (potentialTarget != null && potentialTarget.isAlive() && ModifierLevel.EquipHasModifierlevel(potentialTarget, OrdinaryTinkerModifier.disguiseStaticModifier.getId())) {
            cir.setReturnValue(false);
        }
    }
}
