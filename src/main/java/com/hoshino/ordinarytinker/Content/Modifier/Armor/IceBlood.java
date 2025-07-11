package com.hoshino.ordinarytinker.Content.Modifier.Armor;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDataKeys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class IceBlood extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!isCorrectSlot) return;
        holder.getCapability(TinkerDataCapability.CAPABILITY).resolve().ifPresent(entity -> entity.put(OrdinaryTinkerDataKeys.foodLevelAddition, 5));
        holder.getCapability(TinkerDataCapability.CAPABILITY).resolve().ifPresent(entity -> entity.put(OrdinaryTinkerDataKeys.extraHealth, 90f));
        if (!(holder instanceof Player player)) return;
        player.sendSystemMessage(Component.literal(String.valueOf(player.getMaxHealth())));
    }
}
