package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerMaterialIDs;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

public class OrdinaryTinkerMaterialDefinitionData extends AbstractMaterialDataProvider {
    public OrdinaryTinkerMaterialDefinitionData(PackOutput packOutput) {
        super(packOutput);
    }
    @Override
    protected void addMaterials() {
        addMaterial(OrdinaryTinkerMaterialIDs.armorSteel,   4, ORDER_GENERAL, false);
        addMaterial(OrdinaryTinkerMaterialIDs.soulCheese,   2, ORDER_GENERAL, true);

//        addMaterial(OrdinaryTinkerMaterialIDs.whiteDwarf,   4, ORDER_GENERAL, true);
//        addMaterial(OrdinaryTinkerMaterialIDs.takeru,   3, ORDER_GENERAL, true);
//        addMaterial(OrdinaryTinkerMaterialIDs.experienceSteel,   4, ORDER_GENERAL, true);
//        addMaterial(OrdinaryTinkerMaterialIDs.Kemomimi,   3, ORDER_GENERAL, true);
    }

    @Override
    public String getName() {
        return "OrdinaryTinker's MaterialDefinitions";
    }
}
