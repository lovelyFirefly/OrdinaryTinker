package com.hoshino.ordinarytinker.Content.Modifier.Tool;

import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;

public class SeaBless extends NoLevelsModifier {
    public boolean hidden() {
        return true;
    }
    public boolean shouldDisplay(boolean advanced) {
        return !this.hidden() || advanced;
    }
}
