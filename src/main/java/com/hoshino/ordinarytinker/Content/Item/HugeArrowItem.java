package com.hoshino.ordinarytinker.Content.Item;

import com.hoshino.ordinarytinker.Content.Entity.HugeArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import org.jetbrains.annotations.NotNull;

public class HugeArrowItem extends ArrowItem {
    public HugeArrowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new HugeArrow(pLevel, pShooter);
    }
}
