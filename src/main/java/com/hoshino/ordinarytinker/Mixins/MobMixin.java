package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Entity.Goal.FearGoal;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = Mob.class, priority = 2000)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void injectFearGoal(EntityType<?> type, Level level, CallbackInfo ci) {
        Mob mob = (Mob) (Object) this;
        if (!(mob instanceof Cat) && mob instanceof PathfinderMob pathfinderMob) {
            var goal = new FearGoal<>(pathfinderMob, Player.class, 50, 0.5f, 0.5f, 0.5f,
                    () -> level().getNearestPlayer(mob, 10),
                    player -> player.getPersistentData().getInt("fearfield") > 0);
            mob.goalSelector.addGoal(0, goal);
        }
    }
}
