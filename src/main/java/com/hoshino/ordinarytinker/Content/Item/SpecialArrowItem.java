package com.hoshino.ordinarytinker.Content.Item;

import com.hoshino.ordinarytinker.Content.Entity.SpecialArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SpecialArrowItem extends ArrowItem {
    public SpecialArrowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        var arrow=new SpecialArrow(pLevel,pShooter);
        arrow.setEffectsFromItem(pStack);
        return arrow;
    }
}
