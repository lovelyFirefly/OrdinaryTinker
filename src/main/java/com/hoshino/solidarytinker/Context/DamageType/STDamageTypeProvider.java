package com.hoshino.solidarytinker.Context.DamageType;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;


public class STDamageTypeProvider implements RegistrySetBuilder.RegistryBootstrap<DamageType>{
    public STDamageTypeProvider(){}

    public static void register(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, new STDamageTypeProvider());
    }
    @Override
    public void run(BootstapContext<DamageType> context) {
        context.register(STDamageSource.mercurypoisoning, new DamageType("mercurypoisoning", DamageScaling.NEVER, 0.1f));
    }
}
