package com.hoshino.ordinarytinker.Content.Data.Tag;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class OrdinaryTInkerFluidTagProvider extends FluidTagsProvider {
    public OrdinaryTInkerFluidTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, OrdinaryTinker.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    }
}
