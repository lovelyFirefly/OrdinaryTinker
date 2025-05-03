package com.hoshino.ordinarytinker.Content.Modifier;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class HiddenModifier extends Modifier {
    public boolean havenolevel() {
        return false;
    }

    public boolean hidden() {
        return false;
    }
    public @NotNull Component getDisplayName(int level) {
        if (havenolevel()) {
            return super.getDisplayName();
        } else
            return super.getDisplayName(level);
    }

    public boolean shouldDisplay(boolean advanced) {
        if (hidden()) {
            return advanced;
        } else
            return true;
    }
}
