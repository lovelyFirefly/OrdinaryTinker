package com.hoshino.ordinarytinker.Content.Data.Tag;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class OrdinaryTinkerItemTagProvider extends ItemTagsProvider {
    public OrdinaryTinkerItemTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, OrdinaryTinker.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(@NotNull Provider pProvider) {
        this.tag(OrdinaryTinkerTags.ItemTag.armorSteel_ingot).add(OrdinaryTinkerItem.ArmorSteel_ingot.get());
    }
}
