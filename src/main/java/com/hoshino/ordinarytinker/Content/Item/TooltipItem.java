package com.hoshino.ordinarytinker.Content.Item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipItem extends Item {
    public final List<Component> tooltip;
    public TooltipItem(Properties pProperties,@NotNull List<Component> tooltip) {
        super(pProperties);
        this.tooltip=tooltip;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.addAll(this.tooltip);
        super.appendHoverText(pStack,pLevel,pTooltipComponents,pIsAdvanced);
    }
}
