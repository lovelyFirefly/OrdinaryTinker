package com.hoshino.ordinarytinker.Mixins;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin extends Projectile {

    @Shadow @Nullable public abstract Player getPlayerOwner();

    @Shadow @Final private static EntityDataAccessor<Boolean> DATA_BITING;

    protected FishingHookMixin(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Inject(method = "tick",at = @At("HEAD"))
    private void on(CallbackInfo ci){
        if(this.entityData.get(DATA_BITING)){
            if(this.getPlayerOwner()!=null&&this.getPlayerOwner().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof FishingRodItem item){
                item.use(getPlayerOwner().level(),getPlayerOwner(),InteractionHand.MAIN_HAND);
            }
        }
    }
}
