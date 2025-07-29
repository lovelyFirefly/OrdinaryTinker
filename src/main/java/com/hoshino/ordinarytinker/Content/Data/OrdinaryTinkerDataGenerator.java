package com.hoshino.ordinarytinker.Content.Data;

import com.hoshino.ordinarytinker.Content.Data.DamageType.OrdinaryTinkerDamageTypeProvider;
import com.hoshino.ordinarytinker.Content.Data.Language.CNLanguageProvider;
import com.hoshino.ordinarytinker.Content.Data.Language.ENLanguageProvider;
import com.hoshino.ordinarytinker.Content.Data.Model.OrdinaryTinkerBucketModelProvider;
import com.hoshino.ordinarytinker.Content.Data.Model.OrdinaryTinkerModelProvider;
import com.hoshino.ordinarytinker.Content.Data.Tag.OrdinaryTinkerBlockTagProvider;
import com.hoshino.ordinarytinker.Content.Data.Tag.OrdinaryTinkerDamageTypeTagProvider;
import com.hoshino.ordinarytinker.Content.Data.Tag.OrdinaryTinkerItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OrdinaryTinkerDataGenerator {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void gatherData(@NotNull GatherDataEvent event) {

        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        boolean server = event.includeServer();

        OrdinaryTinkerDamageTypeProvider.register(registrySetBuilder);
        DatapackBuiltinEntriesProvider DatapackRegistryProvider = new DatapackBuiltinEntriesProvider(output, lookupProvider, registrySetBuilder, Set.of(MODID));
        generator.addProvider(server, DatapackRegistryProvider);

        generator.addProvider(server, new OrdinaryTinkerModelProvider(output, MODID, existingFileHelper));
        generator.addProvider(server, new OrdinaryTinkerDamageTypeTagProvider(output, DatapackRegistryProvider.getRegistryProvider(), existingFileHelper));
        generator.addProvider(server, new CNLanguageProvider(output, MODID, "zh_cn"));
        generator.addProvider(server, new ENLanguageProvider(output, MODID, "en_us"));
        generator.addProvider(server, new OrdinaryTinkerBucketModelProvider(output, MODID));
//        弃用掉了
//        OrdinaryTinkerMaterialDefinitionData materials = new OrdinaryTinkerMaterialDefinitionData(output);
//        OrdinaryTinkerMaterialStatsData statsData = new OrdinaryTinkerMaterialStatsData(output, materials);
//        OrdinaryTinkerMaterialTraitsData traitsData = new OrdinaryTinkerMaterialTraitsData(output, materials);
//        generator.addProvider(server, materials);
//        generator.addProvider(server, statsData);
//        generator.addProvider(server, traitsData);

        OrdinaryTinkerBlockTagProvider blockTags = new OrdinaryTinkerBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new OrdinaryTinkerItemTagProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
    }
}
