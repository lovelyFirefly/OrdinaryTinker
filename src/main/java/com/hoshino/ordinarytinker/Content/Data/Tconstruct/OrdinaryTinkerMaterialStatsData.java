package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.FluidEscapeMaterialStats;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerMaterialIDs;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class OrdinaryTinkerMaterialStatsData extends AbstractMaterialStatsDataProvider {
    public OrdinaryTinkerMaterialStatsData(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addArmor();
    }

    @Override
    public String getName() {
        return "OrdinaryTinker's MaterialsStats";
    }

    private void addArmor() {
        addArmorShieldStats(OrdinaryTinkerMaterialIDs.armorSteel,
                PlatingMaterialStats.builder().durabilityFactor(15).armor(2, 4, 5, 2),
                new FluidEscapeMaterialStats(4,5,3,2),
                StatlessMaterialStats.MAILLE);
    }
}
