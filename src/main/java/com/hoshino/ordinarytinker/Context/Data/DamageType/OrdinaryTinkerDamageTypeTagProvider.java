package com.hoshino.ordinarytinker.Context.Data.DamageType;

import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerDamageTypeTagProvider extends DamageTypeTagsProvider {
    public OrdinaryTinkerDamageTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookup, MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        tag(DamageTypeTags.BYPASSES_ARMOR).add(OTDamageTypes.MERCURYPOISONING);
        tag(DamageTypeTags.BYPASSES_COOLDOWN).add(OTDamageTypes.MERCURYPOISONING);
        tag(DamageTypeTags.NO_IMPACT).add(OTDamageTypes.MERCURYPOISONING);
    }
}
