package com.hoshino.ordinarytinker.Context.Data.DamageType;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;

public class OTDamageTypeProvider implements RegistrySetBuilder.RegistryBootstrap<DamageType>{
    public static void register(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, new OTDamageTypeProvider());
    }

    @Override
    public void run(@NotNull BootstapContext<DamageType> context) {
        context.register(OTDamageTypes.MERCURYPOISONING, new DamageType("mercurypoisoning", 0.1f));
    }
}
