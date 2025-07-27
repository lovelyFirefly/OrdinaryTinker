package com.hoshino.ordinarytinker.Content.Util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface ModifyDamageTag {
    void ordinarytinker$addDamageTag(Consumer<List<TagKey<DamageType>>> list);
}
