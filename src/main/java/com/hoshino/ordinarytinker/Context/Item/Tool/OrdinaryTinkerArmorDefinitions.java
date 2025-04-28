package com.hoshino.ordinarytinker.Context.Item.Tool;

import net.minecraft.sounds.SoundEvents;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;

import static slimeknights.tconstruct.TConstruct.getResource;

public class OrdinaryTinkerArmorDefinitions {
    public static final ModifiableArmorMaterial FLUID_PLATE = ModifiableArmorMaterial.create(getResource("fluid_plate"), SoundEvents.BUCKET_EMPTY);
}
