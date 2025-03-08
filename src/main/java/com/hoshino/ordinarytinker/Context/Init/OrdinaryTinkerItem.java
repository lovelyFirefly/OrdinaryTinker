package com.hoshino.ordinarytinker.Context.Init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerItem {
    public static final DeferredRegister<Item> ITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final Supplier<Item> ArmorSteel_ingot=ITEM.register("armorsteel_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> CheeseAlloy_ingot=ITEM.register("cheesealloy_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> LeadAmalgamation_ingot=ITEM.register("leadamalgamation_ingot",()->new Item(new Item.Properties()));
    public OrdinaryTinkerItem(){
    }

    public static void register(IEventBus bus){
        ITEM.register(bus);
    }
}
