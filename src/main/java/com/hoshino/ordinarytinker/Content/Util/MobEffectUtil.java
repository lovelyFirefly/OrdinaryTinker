package com.hoshino.ordinarytinker.Content.Util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class MobEffectUtil {
    public static void directAddMobEffect(LivingEntity livingEntity, MobEffectInstance instance) {
        MobEffectInstance mobeffectinstance = livingEntity.getActiveEffectsMap().get(instance.getEffect());
        if (mobeffectinstance == null) {
            livingEntity.getActiveEffectsMap().put(instance.getEffect(), instance);
            livingEntity.onEffectAdded(instance, null);
        } else if (mobeffectinstance.update(instance)) {
            livingEntity.onEffectUpdated(mobeffectinstance, true, null);
        }
    }
}
