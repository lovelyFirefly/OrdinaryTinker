package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Entity.Goal.FearGoal;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void injectFearGoal(
            EntityType<?> type, Level level,
            CallbackInfo ci
    ) {
        Mob mob = (Mob)(Object)this;
        mob.goalSelector.addGoal(0, new FearGoal(mob, 12.0F, 1.5D));
    }
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void onAiStep(CallbackInfo ci) {
        Mob mob = (Mob)(Object)this;
            Player nearestPlayer = mob.level().getNearestPlayer(mob, 8.0);
            if (nearestPlayer != null && shouldTriggerHeadShake(nearestPlayer, mob)) {
                applyRandomHeadRotation(mob);
            }
    }
    @Unique
    private boolean shouldTriggerHeadShake(Player player, Mob mob) {
        boolean hasItem = ModifierLevel.EquipHasModifierlevel(player, OrdinaryTinkerModifier.hoshinoHaloStaticModifier.getId()) || player.getOffhandItem().is(Items.NETHER_STAR);
        float distanceFactor = 1 - mob.distanceTo(player) / 8.0F;
        float probability = 0.3F * distanceFactor;
        return hasItem && mob.getRandom().nextFloat() < probability;
    }
    @Unique
    private void applyRandomHeadRotation(Mob mob) {
        RandomSource random = mob.getRandom();
        float yawOffset = (random.nextFloat() - 0.5f) * 360;
        float pitchOffset = (random.nextFloat() - 0.5f) * 180;
        mob.setYHeadRot(mob.getYHeadRot() + yawOffset);
        mob.setXRot(mob.getXRot() + pitchOffset);
    }
}
