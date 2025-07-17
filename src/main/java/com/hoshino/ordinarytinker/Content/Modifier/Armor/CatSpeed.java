package com.hoshino.ordinarytinker.Content.Modifier.Armor;

import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.Random;
import java.util.UUID;
import java.util.function.BiConsumer;

public class CatSpeed extends Modifier implements DamageBlockModifierHook, ToolStatsModifierHook, InventoryTickModifierHook, AttributesModifierHook, BreakSpeedModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.DAMAGE_BLOCK, ModifierHooks.TOOL_STATS, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        int rd = new Random().nextInt(10);
        return rd < 3;
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.ATTACK_SPEED.multiply(builder, 1 + 0.7f * modifier.getLevel());
        if (context.hasTag(TinkerTags.Items.RANGED)) {
            ToolStats.VELOCITY.multiply(builder, 1 + 0.7f * modifier.getLevel());
        }
    }

    public static final Attribute[] attributes = new Attribute[]{
            Attributes.ATTACK_SPEED,
            Attributes.FLYING_SPEED,
            Attributes.ARMOR_TOUGHNESS,
            Attributes.FOLLOW_RANGE,
    };

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (isCorrectSlot && holder.tickCount % 200 == 0) {
            holder.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, false, false, true));
        }
    }

    private UUID getUUID(Modifier modifier, EquipmentSlot slot, Attribute attribute) {
        String s = modifier.getId().getNamespace() + slot.getName() + attribute.getDescriptionId();
        return UUID.nameUUIDFromBytes(s.getBytes());
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        var attributeModifier = new AttributeModifier(getUUID(this, slot, Attributes.MOVEMENT_SPEED), Attributes.MOVEMENT_SPEED.getDescriptionId(), 1.5f, AttributeModifier.Operation.MULTIPLY_TOTAL);
        consumer.accept(Attributes.MOVEMENT_SPEED, attributeModifier);
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {

    }
}
