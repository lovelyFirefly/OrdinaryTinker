package com.hoshino.ordinarytinker.Context.MobEffect;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;

public class MercuryPoisoning extends FormatEffect {
    public MercuryPoisoning() {
        super(MobEffectCategory.HARMFUL, 16769263);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        var mercuryPoisoningSource = OTDamageTypes.source(living.level(), OTDamageTypes.MERCURYPOISONING);
        if (living.tickCount % 10 == 0) {
            if (living instanceof EnderMan enderMan) {
                enderMan.hurt(mercuryPoisoningSource, (1.5f * amplifier + 1) + (enderMan.getMaxHealth() * 0.03F * amplifier));
            } else living.hurt(mercuryPoisoningSource, 1.5f * amplifier + 1);
        }
    }
}
