package com.hoshino.ordinarytinker;

import com.hoshino.ordinarytinker.Context.Data.DamageType.OTDamageTypeProvider;
import com.hoshino.ordinarytinker.Context.Data.Language.CNLanguageProvider;
import com.hoshino.ordinarytinker.Context.Data.Language.ENLanguageProvider;
import com.hoshino.ordinarytinker.Context.Data.Model.OTModelProvider;
import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerModifier;
import com.hoshino.ordinarytinker.Context.Network.OTChannel;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.data.DamageTypeProvider;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Locale;
import java.util.function.Supplier;


@Mod(OrdinaryTinker.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = OrdinaryTinker.MODID)
public class OrdinaryTinker {
    public static final String MODID = "ordinarytinker";
    public static OrdinaryTinker instance;
    public OrdinaryTinker() {
        instance = this;
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(EventPriority.NORMAL, false, GatherDataEvent.class, this::gatherData);
        bus.addListener(EventPriority.NORMAL, false, FMLCommonSetupEvent.class, this::commonSetup);
        bus.register(new OrdinaryTinkerModifier());
        OrdinaryTinkerItem.register(bus);
    }
    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        OTChannel.register();
    }
    @SubscribeEvent
    public void gatherData(@NotNull GatherDataEvent event) {
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(
                event.includeClient(),
                (DataProvider.Factory<CNLanguageProvider>)pOutput->new CNLanguageProvider(pOutput,MODID,"zh_cn") {
                }
        );
        generator.addProvider(
                event.includeClient(),
                (DataProvider.Factory<ENLanguageProvider>)pOutput->new ENLanguageProvider(pOutput,MODID,"en_us") {
                }
        );
        generator.addProvider(
                event.includeClient(),
                (DataProvider.Factory<OTModelProvider>) pOutput->new OTModelProvider(pOutput,MODID,existingFileHelper) {
                }
        );
        OTDamageTypeProvider.register(registrySetBuilder);
    }
    public static String prefix(String name) {
        return MODID + "." + name.toLowerCase(Locale.CHINA);
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> TinkerDataCapability.ComputableDataKey<T> createKey(String name, Supplier<T> constructor) {
        return TinkerDataCapability.ComputableDataKey.of(getResource(name), constructor);
    }
}
