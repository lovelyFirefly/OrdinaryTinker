package com.hoshino.ordinarytinker;

import com.hoshino.ordinarytinker.Config.OrdinaryTinkerConfig;
import com.hoshino.ordinarytinker.Network.OTChannel;
import com.hoshino.ordinarytinker.Register.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

import java.util.Locale;
import java.util.function.Supplier;


@Mod(OrdinaryTinker.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = OrdinaryTinker.MODID)
public class OrdinaryTinker {
    public static final String MODID = "ordinarytinker";
    public OrdinaryTinker() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(EventPriority.NORMAL, false, FMLCommonSetupEvent.class, this::commonSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, OrdinaryTinkerConfig.Toolspec, "ordinarytinkertool.toml");
        OrdinaryTinkerTab.register(bus);
        OrdinaryTinkerItem.register(bus);
        OrdinaryTinkerFluid.register(bus);
        OrdinaryTinkerModifier.register(bus);
        OrdinaryTinkerEffect.register(bus);
        OrdinaryTinkerLivingEntity.register(bus);
        OrdinaryTinkerTags.init();
    }
    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(OrdinaryTinkerMaterialStat::setup);
        OTChannel.register();
    }

    public static String prefix(String name) {
        return MODID + "." + name.toLowerCase(Locale.CHINA);
    }

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static <T> TinkerDataCapability.ComputableDataKey<T> createKey(String name, Supplier<T> constructor) {
        return TinkerDataCapability.ComputableDataKey.of(getResource(name), constructor);
    }
    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }

}
