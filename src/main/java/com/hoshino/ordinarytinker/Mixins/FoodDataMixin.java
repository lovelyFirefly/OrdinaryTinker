package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDataKeys;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Optional;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {
    @Shadow
    private int tickTimer;

    @Shadow
    public abstract void addExhaustion(float pExhaustion);

    @Shadow
    private int foodLevel;
    @Unique
    private Player ordinarytinker$player;

    @ModifyArg(method = "eat(IF)V", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 1)
    private int eat(int a) {
        if (ordinarytinker$player == null) return a;
        return ordinarytinker$player.getCapability(TinkerDataCapability.CAPABILITY).resolve()
                .flatMap(cap -> Optional.ofNullable(cap.get(OrdinaryTinkerDataKeys.foodLevelAddition)))
                .filter(cap -> cap > 0)
                .map(p -> p + 20)
                .orElse(a);
    }

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/food/FoodData;saturationLevel:F", ordinal = 3), cancellable = true)
    private void tick(Player pPlayer, CallbackInfo ci) {
        boolean flag = pPlayer.level().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
        if (flag && pPlayer.isHurt() && this.foodLevel >= 6 && ModifierLevel.EquipHasModifierlevel(pPlayer, OrdinaryTinkerModifier.iceBloodStaticModifier.getId())) {
            ++this.tickTimer;
            if (this.tickTimer >= 10) {
                float f = Math.min(this.foodLevel, 6.0F);
                pPlayer.heal(f / 6.0F);
                this.addExhaustion(f);
                this.tickTimer = 0;
            }
        }
        ci.cancel();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void setpPlayer(Player pPlayer, CallbackInfo ci) {
        if (ordinarytinker$player == pPlayer) return;
        ordinarytinker$player = pPlayer;
    }
}
