package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.EventPriority;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;


public class OrdinaryTinkerTags {
    public static boolean tagsLoaded;

    public static void init(){
        ItemTag.init();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, TagsUpdatedEvent.class, event -> tagsLoaded = true);
    }
    public static class ItemTag{
        private static TagKey<Item> local(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }
        private static TagKey<Item> forge(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }
        private static TagKey<Item> vanilla(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }
        public static final TagKey<Item> armorSteel_ingot = forge("armorsteel_ingot");
        public static void init(){}
    }
    public static class BlockTag{

    }
}
