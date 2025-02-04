package com.hoshino.solidarytinker.Context.DamageType;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class STDamageTag extends TagsProvider<DamageType> {
    protected STDamageTag(PackOutput output, ResourceKey<? extends Registry<DamageType>> resourceKey, CompletableFuture<HolderLookup.Provider> providerCompletableFuture, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, resourceKey, providerCompletableFuture, modId, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(STDamageSource.mercurypoisoning);
    }
}
