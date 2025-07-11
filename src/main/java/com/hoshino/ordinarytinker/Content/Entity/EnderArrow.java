package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EnderArrow extends AbstractArrow {
    public EnderArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public EnderArrow(Level pLevel, LivingEntity pShooter) {
        this(OrdinaryTinkerEntity.huge_arrow.get(), pLevel);
        this.setPos(pShooter.getX(), pShooter.getEyeY() - 0.1, pShooter.getZ());
        this.setYRot(pShooter.getYRot());
        this.setXRot(pShooter.getXRot());
        this.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot(), 0, 3, 1);
        setOwner(pShooter);
        Vec3 move = this.getDeltaMovement();
        this.setDeltaMovement(move.scale(5F));
        this.pickup = Pickup.CREATIVE_ONLY;
    }

    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(Items.ENDER_PEARL);
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        this.setNoPhysics(true);
        Vec3 vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        for (int i = 0; i < 4; ++i) {
            this.level().addParticle(ParticleTypes.WITCH, this.getX() + d5 * (double) i / 4.0D, this.getY() + d6 * (double) i / 4.0D, this.getZ() + d1 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }
        if (this.tickCount > 400) {
            this.discard();
        }
    }
}
