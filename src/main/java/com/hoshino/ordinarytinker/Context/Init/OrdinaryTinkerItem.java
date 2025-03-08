package com.hoshino.ordinarytinker.Context.Init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerItem {
    public static final DeferredRegister<Item> ITEM=DeferredRegister.create(Registries.ITEM,MODID);
    protected static List<RegistryObject<Item>> CommonItem=new ArrayList<>(List.of());
    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        CommonItem.add(object);
        return object;
    }
    public static final Supplier<Item> ArmorSteel_ingot=registerCommonMaterials(ITEM,"armorsteel_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> CheeseAlloy_ingot=registerCommonMaterials(ITEM,"cheesealloy_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> LeadAmalgamation_ingot=registerCommonMaterials(ITEM,"leadamalgamation_ingot",()->new Item(new Item.Properties()));
    public OrdinaryTinkerItem(){
    }

    public static void register(IEventBus bus){
        ITEM.register(bus);
    }
}
