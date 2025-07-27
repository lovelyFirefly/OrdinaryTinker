package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ModifyDamageTag;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Consumer;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin implements ModifyDamageTag {
    @Shadow
    @Final
    private Holder<DamageType> type;
    @Unique
    List<TagKey<DamageType>> etst$tagKeyList = null;

    @Inject(method = "is(Lnet/minecraft/tags/TagKey;)Z", at = @At("HEAD"), cancellable = true)
    private void addTag(TagKey<DamageType> pDamageTypeKey, CallbackInfoReturnable<Boolean> cir) {
        if (etst$tagKeyList == null) return;
        cir.setReturnValue(etst$tagKeyList.contains(pDamageTypeKey));
    }
    @Override
    public void ordinarytinker$addDamageTag(Consumer<List<TagKey<DamageType>>> list) {
        if(etst$tagKeyList==null){
            etst$tagKeyList=type.tags().toList();
        }
        list.accept(etst$tagKeyList);
    }
}
