package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Entity.Goal.DynamicAvoidGoal;
import com.hoshino.ordinarytinker.Content.Entity.Goal.FearGoal;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
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
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Mixin(value = Mob.class,priority = 2000)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void injectFearGoal(EntityType<?> type, Level level, CallbackInfo ci) {
        Mob mob = (Mob)(Object)this;
        if(mob instanceof Cat)return;
        var goal = new FearGoal<>((PathfinderMob) mob, Player.class, 50, 1.2, 1.5f, 1.5,
                () -> level().getNearestPlayer(mob, 10),
                player -> ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.fearStaticModifier.getId()) > 0&&mob.getMaxHealth()<=10);
        mob.goalSelector.addGoal(0, goal);
    }
}
