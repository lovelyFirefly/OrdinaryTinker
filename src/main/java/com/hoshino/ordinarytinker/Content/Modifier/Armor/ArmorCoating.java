package com.hoshino.ordinarytinker.Content.Modifier.Armor;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ArmorCoating extends Modifier implements DamageBlockModifierHook, ModifyDamageModifierHook, AttributesModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.DAMAGE_BLOCK, ModifierHooks.MODIFY_HURT, ModifierHooks.ATTRIBUTES);
    }
    private int totalModifierLevel(LivingEntity living){
        return ModifierLevel.getAllSlotModifierlevel(living,this.getId());
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        float value = context.getEntity().getArmorValue() *0.1f * totalModifierLevel(context.getEntity());
        return value > amount;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        float value = context.getEntity().getArmorValue() *0.1f * totalModifierLevel(context.getEntity());
        return amount - value * modifier.getLevel();
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        var attributeModifier=new AttributeModifier(UUID.randomUUID(), Attributes.ARMOR.getDescriptionId(),0.1 * modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE);
        consumer.accept(Attributes.ARMOR,attributeModifier);
    }
}