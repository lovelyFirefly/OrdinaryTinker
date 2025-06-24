package com.hoshino.ordinarytinker.Register;

import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability.TinkerDataKey;

public interface OrdinaryTinkerDataKeys {
    static void init() {}
    TinkerDataKey<Boolean> ShouldDrop = TConstruct.createKey("should_drop");
    TinkerDataKey<Integer> foodLevelAddition = TConstruct.createKey("food_level_addition");
    TinkerDataKey<Float> extraHealth = TConstruct.createKey("extra_health");
}
