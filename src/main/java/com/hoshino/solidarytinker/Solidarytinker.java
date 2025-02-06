package com.hoshino.solidarytinker;

import com.hoshino.solidarytinker.Context.DamageType.STDamageTypeProvider;
import com.hoshino.solidarytinker.Context.Init.SolidarytinkerModifier;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Locale;
import java.util.function.Supplier;


@Mod(Solidarytinker.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Solidarytinker {
    public static final String MODID = "solidarytinker";
    public static Solidarytinker instance;
    public Solidarytinker() {
        instance = this;
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(EventPriority.NORMAL, false, GatherDataEvent.class, this::gatherData);
        bus.addListener(EventPriority.NORMAL, false, FMLCommonSetupEvent.class, this::commonSetup);
        bus.register(new SolidarytinkerModifier());
    }
    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
    }
    @SubscribeEvent
    public void gatherData(@NotNull GatherDataEvent event) {
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        STDamageTypeProvider.register(registrySetBuilder);
        //Damage
    }
    public static String prefix(String name) {
        return MODID + "." + name.toLowerCase(Locale.CHINA);
    }




    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }
    public static <T> TinkerDataCapability.ComputableDataKey<T> createKey(String name, Supplier<T> constructor) {
        return TinkerDataCapability.ComputableDataKey.of(getResource(name), constructor);
    }
}
