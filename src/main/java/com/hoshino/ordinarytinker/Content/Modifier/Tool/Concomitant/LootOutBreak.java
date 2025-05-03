package com.hoshino.ordinarytinker.Content.Modifier.Tool.Concomitant;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class LootOutBreak extends Modifier implements ProcessLootModifierHook {
    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        if(context.getParamOrNull(LootContextParams.BLOCK_STATE)!=null){

        }
    }
}
