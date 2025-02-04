package com.hoshino.solidarytinker;

import com.hoshino.solidarytinker.Context.DamageType.STDamageTypeProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod(solidarytinker.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class solidarytinker {
    public static final String MODID = "solidarytinker";
    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }
    @SubscribeEvent
    static void gatherData(final GatherDataEvent event) {
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        STDamageTypeProvider.register(registrySetBuilder);
    }
}
