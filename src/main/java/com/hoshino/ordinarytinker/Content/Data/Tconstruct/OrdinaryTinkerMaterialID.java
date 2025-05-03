package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerMaterialID {
    private static MaterialId id(String name) {
        return new MaterialId(MODID, name);
    }
    public static final MaterialId ArmorSteel = id("armorsteel");
    public static final MaterialId CheeseAlloy = id("cheesealloy");
    public static final MaterialId LeadAmalgamation = id("leadamalgamation");
}
