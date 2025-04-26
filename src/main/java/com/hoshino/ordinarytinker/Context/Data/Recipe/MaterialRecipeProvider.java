package com.hoshino.ordinarytinker.Context.Data.Recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;

import java.util.function.Consumer;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class MaterialRecipeProvider extends RecipeProvider implements ISmelteryRecipeHelper {
    public MaterialRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    public static final TagKey<Item> GEM_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/gem"));
    public static final TagKey<Item> GEM_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/gem"));
    public static final TagKey<Item> INGOT_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/ingot"));
    public static final TagKey<Item> INGOT_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/ingot"));

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

    }

    @Override
    public @NotNull String getModId() {
        return MODID;
    }
}
