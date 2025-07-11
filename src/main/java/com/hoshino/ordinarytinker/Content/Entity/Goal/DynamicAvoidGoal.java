package com.hoshino.ordinarytinker.Content.Entity.Goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class DynamicAvoidGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final Supplier<T> targetSupplier;
    private final Predicate<T> condition;
    private final double sprintSpeedModifier;

    public DynamicAvoidGoal(PathfinderMob mob, Class<T> avoidClass, float maxDist, double walkSpeed, double sprintSpeed, double sprintSpeedModifier, Supplier<T> targetSupplier, Predicate<T> condition) {
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
        if (toAvoid != null && toAvoid.isSprinting()) {
            mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier * 1.2);
        } else {
            super.tick();
        }
    }
}