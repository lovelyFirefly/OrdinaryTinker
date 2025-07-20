package com.hoshino.ordinarytinker.Mixins;

import com.hoshino.ordinarytinker.Content.Util.ChangeField;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(ToolStack.class)
public abstract class ToolstackMixin implements IToolStackView, ChangeField {
    @Shadow(remap = false) @Final private CompoundTag nbt;

    @Shadow(remap = false) @Final protected static String TAG_DAMAGE;

    @Override
    public void ordinarytinker$ChangeDamageField(int damage) {
        nbt.putInt(TAG_DAMAGE, damage);
    }
}
