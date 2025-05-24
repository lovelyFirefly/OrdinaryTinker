package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class OrdinaryTinkerMaterialDefinitionData extends AbstractMaterialDataProvider {
    public OrdinaryTinkerMaterialDefinitionData(PackOutput packOutput) {
        super(packOutput);
    }
    @Override
    protected void addMaterials() {
    }

    @Override
    public String getName() {
        return "OrdinaryTinker's MaterialDefinitions";
    }
}
