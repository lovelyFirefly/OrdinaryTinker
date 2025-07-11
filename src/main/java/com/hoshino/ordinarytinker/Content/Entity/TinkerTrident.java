package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Config.OrdinaryTinkerConfig;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;

import javax.annotation.Nullable;

public class TinkerTrident extends AbstractArrow {

    static {
        new ItemStack(Items.TRIDENT);
    }

    private final ItemStack tridentItem;

    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;

    public TinkerTrident(Level level, LivingEntity entity, ItemStack stack) {
        super(EntityType.TRIDENT, entity, level);
        this.tridentItem = stack.copy();
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getOwner();
        if (entity instanceof Player player) {
            int i = ModifierUtil.getModifierLevel(this.tridentItem, OrdinaryTinkerModifier.loyalStaticModifier.getId());
            if (i > 0 && (this.dealtDamage || this.isNoPhysics())) {
                if (!this.isAcceptibleReturnOwner()) {
                    if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
                        this.spawnAtLocation(this.getPickupItem(), 0.1F);
                    }
                    this.discard();
                } else {
                    this.setNoPhysics(true);
                    Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                    this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double) i, this.getZ());
                    if (this.level().isClientSide) {
                        this.yOld = this.getY();
                    }

                    int Value = OrdinaryTinkerConfig.TridentLoyalSpeed.get();
                    double d0 = Value / 10D * (double) i;
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                    if (this.clientSideReturnTridentTickCount == 0) {
                        this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                    }
                    ++this.clientSideReturnTridentTickCount;
                }
            }
        }
        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    protected @NotNull ItemStack getPickupItem() {
        return this.tridentItem.copy();
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 var10000, Vec3 var10001) {
        return this.dealtDamage ? null : super.findHitEntity(var10000, var10001);
    }

    @Override
    public void setBaseDamage(double a) {
        this.baseDamage = a;
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        float f = (float) this.getDeltaMovement().length();
        float i = (float) Mth.clamp((double) f * this.baseDamage, 0.0D, Float.MAX_VALUE);
        Entity entity1 = this.getOwner();
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (!(entity1 instanceof Player player)) return;
        ToolAttackUtil.attackEntity(tridentItem, player, entity);
        DamageSource damagesource = OrdinaryTinkerDamageTypes.source(player.level(), OrdinaryTinkerDamageTypes.PlayerSoulgeAttack);
        if (entity.hurt(damagesource, i)) {
            return;
        }
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;
        if (this.level() instanceof ServerLevel && this.level().isRaining() && this.canSummon()) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                if (lightningbolt != null) {
                    lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningbolt.setCause(player instanceof ServerPlayer ? (ServerPlayer) player : null);
                    this.level().addFreshEntity(lightningbolt);
                }
                soundevent = SoundEvents.TRIDENT_THUNDER;
                f1 = 5.0F;
            }
            this.playSound(soundevent, f1, 1.0F);
        }

    }

    public boolean canSummon() {
        return ModifierUtil.getModifierLevel(this.tridentItem, OrdinaryTinkerModifier.lightningBoltStaticModifier.getId()) > 0;
    }

    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("Trident", this.tridentItem.save(new CompoundTag()));
        tag.putBoolean("DealtDamage", this.dealtDamage);
    }

    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double re, double ce, double ae) {
        return true;
    }
}