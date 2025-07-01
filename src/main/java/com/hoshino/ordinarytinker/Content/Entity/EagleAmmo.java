package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Content.Particle.ParticleType.StarFallParticleType;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEntity;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EagleAmmo extends BaseFallenStar{
    public EagleAmmo(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public EagleAmmo(LivingEntity owner, Level level, Vec3 targetPosition) {
        super(OrdinaryTinkerEntity.eagle_ammo.get(), owner, level, targetPosition);
    }

    @Override
    protected void shockWaveHurt(Mob mob, Player player) {
        mob.hurt(this.damageSources().fellOutOfWorld(),10);
        mob.setDeltaMovement(new Vec3(1,1,1));
    }
    @Override
    protected void onArrived(ServerPlayer player) {
        super.onArrived(player);
        this.directHurtLiving(this.damageSources().fellOutOfWorld(),10,5);
        this.level().explode(this, getTargetPosition().x, getTargetPosition().y, getTargetPosition().z, 5, true, Level.ExplosionInteraction.TNT);
        if (!this.level().isClientSide) {
            var particle = new StarFallParticleType(true, 20, 0xf8ffb2, 1, 1, 10, getTargetPosition());
            player.serverLevel().sendParticles(particle, getTargetPosition().x(), getTargetPosition().y() + 0.05, getTargetPosition().z(), 1, 0, 0, 0, 0.25);
            level().playSound(null,player.getOnPos(), OrdinaryTinkerSound.starHit.get(), SoundSource.AMBIENT,0.5f,1);
        }
    }

    @Override
    protected @NotNull ParticleOptions getTailParticleType() {
        return ParticleTypes.SMOKE;
    }
    @Override
    public ItemStack getItem() {
        return new ItemStack(OrdinaryTinkerItem.eagle_ammo.get());
    }
}
