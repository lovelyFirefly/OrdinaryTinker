package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Content.Particle.ParticleType.StarFallParticleType;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEntity;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class FallenStar extends Projectile implements ItemSupplier {
    private Vec3 targetPosition;
    private int arrivedTime;
    private boolean isArrived;

    public FallenStar(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FallenStar(Level level, Vec3 targetPosition, double range, LivingEntity owner) {
        super(OrdinaryTinkerEntity.fallen_star.get(), level);
        setOwner(owner);
        this.targetPosition = targetPosition;
        this.setPos(targetPosition.add(new Vec3(30, 50, 30)));
        this.setDeltaMovement(targetPosition.subtract(this.position()).scale(0.01f));
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > 500) {
            this.discard();
        }
        if (this.isArrived) {
            arrivedTime++;
        }
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult) hitresult).getBlockPos();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level().getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level(), blockpos, blockstate, this, (TheEndGatewayBlockEntity) blockentity);
                }
                flag = true;
            }
        }
        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                this.level().addParticle(ParticleTypes.BUBBLE, d2 - vec3.x * 0.25D, d0 - vec3.y * 0.25D, d1 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
            }
            f = 0.8F;
        } else {
            f = 0.99F;
        }
        this.setDeltaMovement(vec3.scale(f));
        if (!this.isNoGravity()) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y, vec31.z);
        }
        this.setPos(d2, d0, d1);
        if (this.level().isClientSide) return;
        if (this.getOwner() instanceof ServerPlayer player) {
            for (int i = 0; i < 4; ++i) {
                double x = vec3.x;
                double y = vec3.y;
                double z = vec3.z;
                player.serverLevel().sendParticles(ParticleTypes.SMOKE, this.getX() + x * (double) i / 4.0D, this.getY() + y * (double) i / 4.0D, this.getZ() + z * (double) i / 4.0D, 10, 0, 0, 0, 0.25);
            }
            if (this.level().getBlockState(this.blockPosition()).isSolid() && !isArrived) {
                onArrived(player);
            }
            if (isArrived && arrivedTime < 40) {
                var pos = new BlockPos((int) targetPosition.x, (int) targetPosition.y, (int) targetPosition.z);
                AABB aabb = new AABB(pos).inflate(50);
                List<Mob> mobList = this.level().getEntitiesOfClass(Mob.class, aabb, LivingEntity::isAlive);
                for (Mob mob : mobList) {
                    var distance = mob.position().subtract(targetPosition).length();
                    if (distance < arrivedTime - 3 || distance > arrivedTime + 3) continue;
                    this.shockWaveHurt(mob, player);
                }
            }
        }
    }

    private void onArrived(ServerPlayer player) {
        if (targetPosition == null) return;
        this.isArrived = true;
        this.setDeltaMovement(new Vec3(0, 0, 0));
        this.level().explode(this, targetPosition.x, targetPosition.y, targetPosition.z, 5, false, Level.ExplosionInteraction.BLOCK);
        if (!this.level().isClientSide) {
            var particle = new StarFallParticleType(true, 20, 0x5555ff, 1, 1, 10, targetPosition);
            player.serverLevel().sendParticles(particle, targetPosition.x(), targetPosition.y() + 0.05, targetPosition.z(), 1, 0, 0, 0, 0.25);
            level().playSound(null, player.getOnPos(), OrdinaryTinkerSound.starHit.get(), SoundSource.AMBIENT, 0.5f, 1);
        }
    }

    private void shockWaveHurt(Mob victims, Player attacker) {
        victims.hurt(OrdinaryTinkerDamageTypes.source(this.level(), OrdinaryTinkerDamageTypes.PlayerSoulgeAttack, attacker), 20);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(OrdinaryTinkerItem.star_debris.get());
    }
}
