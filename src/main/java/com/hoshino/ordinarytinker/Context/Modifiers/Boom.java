package com.hoshino.ordinarytinker.Context.Modifiers;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;

public class Boom extends Modifier implements ValidateModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.VALIDATE);
    }

    @Override
    public @Nullable Component validate(IToolStackView tool, @NotNull ModifierEntry modifier) {
        for(ModifierEntry entry:tool.getModifierList()){
            if(entry instanceof IncrementalModifierEntry entry1){
                entry1.merge(entry);
            }
        }
        return null;
    }
}
