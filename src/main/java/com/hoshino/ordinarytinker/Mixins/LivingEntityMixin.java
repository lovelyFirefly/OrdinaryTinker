package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.LivingPositionRecord;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypeTag;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDataKeys;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingPositionRecord {
    @Unique
    public boolean ordinarytinker$shouldCancelKnockBack = false;
    @Unique
    private Vec3 ordinarytinker$lastPos = Vec3.ZERO;

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Unique
    private static float ordinarytinker$getDamageAfterAbsorb(float pDamage, float pTotalArmor, float pToughnessAttribute) {
        float f = 2.0F + pToughnessAttribute / 4.0F;
        float f1 = Mth.clamp(pTotalArmor - pDamage / f, pTotalArmor * 0.2F, 20.0F);
        return pDamage * (1.0F - f1 / 25.0F);
    }

    @Shadow
    public abstract int getArmorValue();

    @Shadow
    public abstract double getAttributeValue(Attribute pAttribute);

    @Inject(method = "tick", at = @At("TAIL"))
    private void setLastPos(CallbackInfo ci) {
        this.ordinarytinker$lastPos = this.position();
    }

    @Inject(method = "getMaxHealth", at = @At("HEAD"), cancellable = true)
    private void getMaxHealth(CallbackInfoReturnable<Float> cir) {
        this.getCapability(TinkerDataCapability.CAPABILITY).resolve()
                .flatMap(holder -> Optional.ofNullable(holder.get(OrdinaryTinkerDataKeys.extraHealth)))
                .ifPresent(cir::setReturnValue);
    }

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void setDamageAfterArmor(DamageSource pDamageSource, float pDamageAmount, CallbackInfoReturnable<Float> cir) {
        var LivingEntity = (LivingEntity) (Object) this;
        if (!(LivingEntity instanceof Player player)) return;
        int level = ModifierLevel.getTotalArmorModifierlevel(player, OrdinaryTinkerModifier.ironHeartStaticModifier.getId());
        if (level > 0) {
            cir.setReturnValue(ordinarytinker$getDamageAfterAbsorb(pDamageAmount, this.getArmorValue(), (float) this.getAttributeValue(Attributes.ARMOR_TOUGHNESS)));
        }
    }

    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V"))
    private void set(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        if (pSource.is(OrdinaryTinkerDamageTypeTag.AVOID_KNOCK)) {
            ordinarytinker$shouldCancelKnockBack = true;
        }
    }

    @Inject(method = "knockback", at = @At("HEAD"), cancellable = true)
    private void knock(double pStrength, double pX, double pZ, CallbackInfo ci) {
        if (ordinarytinker$shouldCancelKnockBack) {
            ordinarytinker$shouldCancelKnockBack = false;
            ci.cancel();
        }
    }

    @Unique
    @Override
    public Vec3 ordinarytinker$getLastPosition() {
        return this.ordinarytinker$lastPos;
    }

    @Unique
    @Override
    public boolean ordinarytinker$movedinLastTick(double offset) {
        var nowPos = this.position();
        var lastpos = ordinarytinker$lastPos;
        return Math.abs(nowPos.x - lastpos.x) > offset ||
                Math.abs(nowPos.y - lastpos.y) > offset ||
                Math.abs(nowPos.z - lastpos.z) > offset;
    }
}
