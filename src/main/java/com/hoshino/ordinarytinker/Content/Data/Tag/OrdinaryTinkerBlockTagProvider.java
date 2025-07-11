package com.hoshino.ordinarytinker.Content.Data.Tag;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OrdinaryTinkerBlockTagProvider extends BlockTagsProvider {
    public OrdinaryTinkerBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OrdinaryTinker.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
    }
}
