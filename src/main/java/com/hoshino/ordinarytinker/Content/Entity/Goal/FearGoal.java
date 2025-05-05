package com.hoshino.ordinarytinker.Content.Entity.Goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class FearGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final Supplier<T> targetSupplier;
    private final Predicate<T> condition;
    private final double sprintSpeedModifier;

    private float shakeIntensity = 30.0f;
    private float shakeSpeed = 0.2f;
    private float maxIntensity = 80.0f;

    public FearGoal(PathfinderMob mob, Class<T> avoidClass, float maxDist, double walkSpeed, double sprintSpeed, double sprintSpeedModifier, Supplier<T> targetSupplier, Predicate<T> condition) {
        super(mob, avoidClass, maxDist, walkSpeed, sprintSpeed);
        this.targetSupplier = targetSupplier;
        this.condition = condition;
        this.sprintSpeedModifier = sprintSpeedModifier;
    }

    @Override
    public boolean canUse() {
        T potentialTarget = targetSupplier.get();
        if (potentialTarget != null && potentialTarget.isAlive() && condition.test(potentialTarget)) {
            this.toAvoid = potentialTarget;
            return super.canUse();
        }
        return false;
    }

    @Override
    public void tick() {
        if(toAvoid==null)return;
        if (toAvoid.isSprinting()) {
            mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier * 1.2);
        } else {
            super.tick();
        }
        updateHeadShaking();
    }

    private void updateHeadShaking() {
        if (canUse() && toAvoid != null) {
            long gameTime = mob.level().getGameTime();
            float wave1 = (float) Math.sin(gameTime * shakeSpeed) * shakeIntensity;
            float wave2 = (float) Math.cos(gameTime * shakeSpeed * 0.7f) * (shakeIntensity * 0.6f);
            float combinedWave = (wave1 + wave2) * 0.8f;
            float distanceFactor = (float) (1.0 - mob.distanceTo(toAvoid)/15.0);
            float dynamicIntensity = Math.min(maxIntensity, shakeIntensity + 30.0f * distanceFactor);
            mob.yHeadRot = mob.yBodyRot + combinedWave * dynamicIntensity;
            if (mob.getRandom().nextFloat() < 0.3f) {
                mob.yHeadRot += (mob.getRandom().nextFloat() - 0.5f) * 15.0f;
            }
        } else {
            mob.yHeadRot = mob.yBodyRot;
        }
    }
    public FearGoal<T> setShakeParams(float baseIntensity, float speed, float max) {
        this.shakeIntensity = baseIntensity;
        this.shakeSpeed = speed;
        this.maxIntensity = max;
        return this;
    }
}