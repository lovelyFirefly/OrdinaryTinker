package com.hoshino.ordinarytinker.Context.DamageType;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OTDamageTypes {

    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID,name));
    }
    public OTDamageTypes(){}

    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct, causing);
    }

    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity entity) {
        return source(level, type, entity, entity);
    }

    public static DamageSource source(Level level, ResourceKey<DamageType> type) {
        return source(level, type, null, null);
    }
    public static final ResourceKey<DamageType> MERCURYPOISONING = create("mercurypoisoning");
}
