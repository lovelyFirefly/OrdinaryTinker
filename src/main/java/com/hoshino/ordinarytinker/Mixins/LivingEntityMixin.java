package com.hoshino.ordinarytinker.Mixins;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class LivingEntityMixin{
    @ModifyArg(method = "hurt",at = @At(value = "HEAD"))
    public boolean truehurt(){
        return false;
    }
}
