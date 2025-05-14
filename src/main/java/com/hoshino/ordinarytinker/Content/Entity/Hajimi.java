package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Content.DamageType.OTDamageTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Hajimi extends Cat {
    public Hajimi(EntityType<? extends Cat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Cat.createAttributes().add(Attributes.ATTACK_DAMAGE,16F);
    }
    public float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(OTDamageTypes.source(this.level(),OTDamageTypes.SpecailCatAttack,this), this.getAttackDamage());
        if (flag) {
            this.playSound(SoundEvents.FOX_BITE, 1.0F, this.getVoicePitch());
        }
        return flag;
    }
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof LivingEntity lv) {
            if (!this.isTame()) {
                this.setTarget(lv);
            }
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected void reassessTameGoals() {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new HajimiAttackGoal(this,1.2D, true));
        this.goalSelector.addGoal(4, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(5, new CatLieOnBedGoal(this, 1.1D, 8));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        this.goalSelector.addGoal(7, new CatSitOnBlockGoal(this, 0.8D));
        this.goalSelector.addGoal(8, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(9, new OcelotAttackGoal(this));
        this.goalSelector.addGoal(10, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8D, 1.0000001E-5F));
        this.goalSelector.addGoal(12, new LookAtPlayerGoal(this, Player.class, 10.0F));
    }
    static class HajimiAttackGoal extends MeleeAttackGoal {
        public HajimiAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }
        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double attackRange = this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + enemy.getBbWidth();
            if (distToEnemySqr <= attackRange && this.getTicksUntilNextAttack() <= 0) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(enemy);
            }
        }
    }

}