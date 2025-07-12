package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerMaterialIDs;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerMaterialStat;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
@Deprecated
public class OrdinaryTinkerMaterialTraitsData extends AbstractMaterialTraitDataProvider {
    public OrdinaryTinkerMaterialTraitsData(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(OrdinaryTinkerMaterialIDs.armorSteel, OrdinaryTinkerModifier.armorCoatingStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.armorSteel, MaterialRegistry.RANGED, OrdinaryTinkerModifier.armorCoatingStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.armorSteel, MaterialRegistry.ARMOR, OrdinaryTinkerModifier.armorCoatingStaticModifier, OrdinaryTinkerModifier.ironHeartStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.armorSteel, OrdinaryTinkerMaterialStat.SOULGE_HEART, OrdinaryTinkerModifier.armorCoatingStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.armorSteel, OrdinaryTinkerMaterialStat.FLUID_ESCAPE, OrdinaryTinkerModifier.armorCoatingStaticModifier, OrdinaryTinkerModifier.ironHeartStaticModifier);

        addDefaultTraits(OrdinaryTinkerMaterialIDs.soulCheese, OrdinaryTinkerModifier.dementorsStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.soulCheese, MaterialRegistry.ARMOR, OrdinaryTinkerModifier.dementorsStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.soulCheese, OrdinaryTinkerMaterialStat.SOULGE_HEART, OrdinaryTinkerModifier.dementorsStaticModifier);
        addTraits(OrdinaryTinkerMaterialIDs.soulCheese, OrdinaryTinkerMaterialStat.FLUID_ESCAPE, OrdinaryTinkerModifier.dementorsStaticModifier);
    }

    @Override
    public @NotNull String getName() {
        return "OrdinaryTinker's MaterialsTraits";
    }
}
