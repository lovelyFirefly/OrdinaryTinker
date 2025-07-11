package com.hoshino.ordinarytinker.Content.Util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EquipmentHelper {
    public static List<ItemStack> getEquipmentList(LivingEntity living) {
        List<ItemStack> stacks = new ArrayList<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            stacks.add(living.getItemBySlot(slot));
        }
        return stacks;
    }
}
