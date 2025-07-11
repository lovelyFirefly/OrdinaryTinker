package com.hoshino.ordinarytinker.Content.Modifier.General;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;


public class Dementors extends Modifier implements MeleeHitModifierHook, OnAttackedModifierHook, TooltipModifierHook {
    private static final ResourceLocation SOUL = OrdinaryTinker.getResource("soul");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.ON_ATTACKED, ModifierHooks.TOOLTIP);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        int soulAmount = tool.getPersistentData().getInt(SOUL);
        tool.getPersistentData().putInt(SOUL, Math.min(10 + soulAmount, 20 * modifier.getLevel()));
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.getEntity() != null) {
            int soulAmount = tool.getPersistentData().getInt(SOUL);
            tool.getPersistentData().putInt(SOUL, Math.min(10 + soulAmount, 20 * ModifierLevel.getTotalArmorModifierlevel(context.getEntity(), this.getId())));
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        int soulAmount = tool.getPersistentData().getInt(SOUL);
        tooltip.add(DynamicComponentUtil.BreathColorfulText.getColorfulText("tooltip.modifier.demetors", String.valueOf(soulAmount), 0x906738, 20, 4000, true));
    }
}
