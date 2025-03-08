package com.hoshino.ordinarytinker;

import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerModifier;
import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerTab;
import com.hoshino.ordinarytinker.Context.Network.OTChannel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
//        OrdinaryTinkerModifier.register(bus);
        OrdinaryTinkerTab.register(bus);
        OrdinaryTinkerItem.register(bus);
    }
    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        OTChannel.register();
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
