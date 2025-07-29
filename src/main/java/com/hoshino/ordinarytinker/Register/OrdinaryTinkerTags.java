package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.EventPriority;


public class OrdinaryTinkerTags {
    public static boolean tagsLoaded;

    public static void init() {
        ItemTag.init();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, TagsUpdatedEvent.class, event -> tagsLoaded = true);
    }

    public static class ItemTag {
        public static final TagKey<Item> armorSteel_ingot = forge("armorsteel_ingot");
        private static final TagKey<Item> bottle = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "water_bottles"));

        private static TagKey<Item> local(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }

        private static TagKey<Item> forge(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }

        private static TagKey<Item> vanilla(String name) {
            return TagKey.create(Registries.ITEM, OrdinaryTinker.getResource(name));
        }

        public static void init() {
        }
    }

    public static class BlockTag {

    }
}
