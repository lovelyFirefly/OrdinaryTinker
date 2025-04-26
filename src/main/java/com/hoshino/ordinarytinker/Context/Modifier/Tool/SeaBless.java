package com.hoshino.ordinarytinker.Context.Modifier.Tool;

import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;

public class SeaBless extends NoLevelsModifier {
    public boolean hidden() {
        return true;
    }
    public boolean shouldDisplay(boolean advanced) {
        return !this.hidden() || advanced;
    }
}
