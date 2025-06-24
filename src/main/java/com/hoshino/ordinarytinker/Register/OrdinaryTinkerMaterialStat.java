package com.hoshino.ordinarytinker.Register;


import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.FluidEscapeMaterialStats;
import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.SoulGeHeartMaterialStats;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

public class OrdinaryTinkerMaterialStat {
    public OrdinaryTinkerMaterialStat(){}
    public static final MaterialStatsId SOULGE_HEART = new MaterialStatsId(OrdinaryTinker.getResource("soulge_heart"));
    public static final MaterialStatsId FLUID_ESCAPE = new MaterialStatsId(OrdinaryTinker.getResource("fluid_escape"));

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(SoulGeHeartMaterialStats.TYPE, MaterialRegistry.MELEE_HARVEST);
        registry.registerStatType(FluidEscapeMaterialStats.TYPE, MaterialRegistry.ARMOR);
    }
}
