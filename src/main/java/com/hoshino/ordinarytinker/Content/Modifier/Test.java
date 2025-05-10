package com.hoshino.ordinarytinker.Content.Modifier;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.BiConsumer;


public class Test extends Modifier implements InventoryTickModifierHook, AttributesModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK,ModifierHooks.ATTRIBUTES);
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
    }
    public Attribute[] armorAttribute=new Attribute[]{
            Attributes.ARMOR,Attributes.ARMOR_TOUGHNESS,Attributes.MOVEMENT_SPEED,Attributes.MAX_HEALTH,Attributes.FLYING_SPEED,Attributes.JUMP_STRENGTH,Attributes.KNOCKBACK_RESISTANCE
    };
    public Attribute[] weaponAttribute=new Attribute[]{
            Attributes.ATTACK_SPEED,Attributes.ATTACK_DAMAGE,Attributes.ATTACK_KNOCKBACK
    };
    public UUID UUIDFromWeapon(EquipmentSlot slot, ModifierId modifierId){
        return  UUID.nameUUIDFromBytes((slot.getName() +modifierId.toString()).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if(tool.hasTag(TinkerTags.Items.ARMOR)){
            for (Attribute attribute : armorAttribute) {
                AttributeModifier attributeModifier = new AttributeModifier(UUIDFromWeapon(slot,this.getId()), attribute.getDescriptionId(), 1, AttributeModifier.Operation.ADDITION);
                consumer.accept(attribute, attributeModifier);
            }
        }
    }
}
