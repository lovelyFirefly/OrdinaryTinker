package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public interface OrdinaryTinkerDamageTypeTag {
    TagKey<DamageType> AVOID_KNOCK = create("avoid_knock");
    private static TagKey<DamageType> create(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, OrdinaryTinker.getResource(name));
    }
}
