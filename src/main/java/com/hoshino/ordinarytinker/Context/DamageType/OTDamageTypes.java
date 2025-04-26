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
    //Source的Create方法
    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID,name));
    }
    public OTDamageTypes(){}

    //生成Source
    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity direct, @Nullable Entity causing) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), direct, causing);
    }

    //有源
    public static DamageSource source(Level level, ResourceKey<DamageType> type, @Nullable Entity entity) {
        return source(level, type, entity, entity);
    }
    //无源
    public static DamageSource source(Level level, ResourceKey<DamageType> type) {
        return source(level, type, null, null);
    }
    //DamageType项
    public static final ResourceKey<DamageType> MERCURYPOISONING = create("mercurypoisoning");

    public static final ResourceKey<DamageType> PlayerSoulgeAttack = create("playersoulgeattack");
}
