package com.hoshino.ordinarytinker.Event.Common;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID)
public class OTToolEvent {
    private static void applyDamage(LivingEntity entity, LivingEntity attacker, boolean should) {
        var data = entity.getPersistentData();
        if (should && attacker instanceof Player player) {
            entity.hurt(player.damageSources().playerAttack(player), Float.MAX_VALUE);
            data.remove("ready_to_die");
        } else {
            entity.hurt(OTDamageTypes.source(entity.level(),OTDamageTypes.MERCURYPOISONING), Float.MAX_VALUE);
            data.remove("ready_to_die");
        }
    }
    @SubscribeEvent
    public static void soulGe(LivingEvent.LivingTickEvent event) {
        var livingEntity = event.getEntity();
        var entityData = livingEntity.getPersistentData();
        var dieTick = entityData.getInt("ready_to_die");
        var attacker = livingEntity.getLastHurtByMob();
        if (entityData.contains("ready_to_die")) {
            entityData.putInt("ready_to_die", dieTick - 1);
            switch (dieTick) {
                case 1 -> applyDamage(livingEntity, attacker, true);
                case 2 - 4 -> applyDamage(livingEntity, attacker, livingEntity.onGround());
                case 5 -> livingEntity.setDeltaMovement(new Vec3(0, -2.5, 0));
            }
        }
    }
    @SubscribeEvent
    public static void soulGeAttackTime(LivingEvent.LivingTickEvent event) {
        var livingEntity = event.getEntity();
        var entityData = livingEntity.getPersistentData();
        var targetedTimes = entityData.getInt("targeted");
        if(targetedTimes>0&&livingEntity.tickCount%200==0){
            entityData.putInt("targeted",targetedTimes-1);
        }
    }
}
