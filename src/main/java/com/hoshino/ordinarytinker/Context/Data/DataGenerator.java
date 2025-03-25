package com.hoshino.ordinarytinker.Context.Data;

import com.hoshino.ordinarytinker.Context.Data.DamageType.OTDamageTypeProvider;
import com.hoshino.ordinarytinker.Context.Data.Language.CNLanguageProvider;
import com.hoshino.ordinarytinker.Context.Data.Language.ENLanguageProvider;
import com.hoshino.ordinarytinker.Context.Data.Model.OTBucketModelProvider;
import com.hoshino.ordinarytinker.Context.Data.Model.OTModelProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
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
public final class DataGenerator {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void gatherData(@NotNull GatherDataEvent event) {

        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        boolean server = event.includeServer();

        OTDamageTypeProvider.register(registrySetBuilder);
        DatapackBuiltinEntriesProvider DatapackRegistryProvider = new DatapackBuiltinEntriesProvider(output, lookupProvider, registrySetBuilder, Set.of(MODID));
        generator.addProvider(server, DatapackRegistryProvider);

        generator.addProvider(server, new OTModelProvider(output,MODID, existingFileHelper));
        generator.addProvider(server, new CNLanguageProvider(output,MODID, "zh_cn"));
        generator.addProvider(server, new ENLanguageProvider(output,MODID, "en_us"));
        generator.addProvider(server, new OTBucketModelProvider(output,MODID));
    }
}
