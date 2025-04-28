package com.hoshino.ordinarytinker.Register;


import com.hoshino.ordinarytinker.Context.Item.Tool.Stats.FluidEscapeMaterialStats;
import com.hoshino.ordinarytinker.Context.Item.Tool.Stats.SoulGeHeartMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;

public class OrdinaryTinkerMaterialStat {
    public OrdinaryTinkerMaterialStat(){}

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(SoulGeHeartMaterialStats.TYPE, MaterialRegistry.MELEE_HARVEST);
        registry.registerStatType(FluidEscapeMaterialStats.TYPE, MaterialRegistry.ARMOR);
    }
}
