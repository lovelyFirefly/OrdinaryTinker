package com.hoshino.ordinarytinker.Context.Init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerItem {
    public static final DeferredRegister<Item> ITEM=DeferredRegister.create(Registries.ITEM,MODID);
    public static final Supplier<Item> curse_ingot=ITEM.register("curse_ingot",()->new Item(new Item.Properties()));
}
