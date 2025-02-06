package com.hoshino.solidarytinker.Context.DamageType;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

import static com.hoshino.solidarytinker.Context.DamageType.STDamageTypes.MERCURYPOISONING;
import static com.hoshino.solidarytinker.Solidarytinker.prefix;


public class STDamageTypeProvider implements RegistrySetBuilder.RegistryBootstrap<DamageType>{

    public static void register(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, new STDamageTypeProvider());
    }
    @Override
    public void run(BootstapContext<DamageType> context) {
        context.register(MERCURYPOISONING, new DamageType(prefix("mercurypoisoning"), DamageScaling.NEVER, 0.1f));
    }
}
