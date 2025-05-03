package com.hoshino.ordinarytinker.Content.Data.DamageType;

import com.hoshino.ordinarytinker.Content.DamageType.OTDamageTypes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;

public class OrdinaryTinkerDamageTypeProvider implements RegistrySetBuilder.RegistryBootstrap<DamageType>{
    public static void register(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, new OrdinaryTinkerDamageTypeProvider());
    }

    @Override
    public void run(@NotNull BootstapContext<DamageType> context) {
        context.register(OTDamageTypes.MERCURYPOISONING, new DamageType("mercurypoisoning", 0.1f, DamageEffects.THORNS));
        context.register(OTDamageTypes.PlayerSoulgeAttack, new DamageType("playersoulgeattack", 0.1f));
        context.register(OTDamageTypes.SpecialReflect, new DamageType("specialreflect", 0.1f,DamageEffects.BURNING));
        context.register(OTDamageTypes.SpecailCatAttack, new DamageType("specialcatattack", 0.1f,DamageEffects.POKING));
    }
}
