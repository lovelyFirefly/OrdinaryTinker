package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Entity.Goal.DynamicAvoidGoal;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Mixin(Creeper.class)
public abstract class CreeperGoalMixin extends Monster implements PowerableMob {
    protected CreeperGoalMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void registerGoal(CallbackInfo ci) {
        var goal = new DynamicAvoidGoal<>(this, Player.class, 10, 3.0, 2.0, 3.0,
                () -> level().getNearestPlayer(this, 15),
                player -> ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.mariHaloStaticModifier.getId()) > 0);
        this.goalSelector.addGoal(1, goal);
    }
}
