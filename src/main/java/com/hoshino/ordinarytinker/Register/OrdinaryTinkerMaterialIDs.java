package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class OrdinaryTinkerMaterialIDs {
    public static final MaterialId armorSteel = id("armorsteel");
    public static final MaterialId experienceSteel = id("experiencesteel");
    public static final MaterialId Kemomimi = id("kemomimi");
    public static final MaterialId soulCheese = id("soulcheese");
    public static final MaterialId takeru = id("takeru");
    public static final MaterialId whiteDwarf = id("whitedwarf");

    private static MaterialId id(String name) {
        return new MaterialId(OrdinaryTinker.MODID, name);
    }

    public static void init() {
    }
}
