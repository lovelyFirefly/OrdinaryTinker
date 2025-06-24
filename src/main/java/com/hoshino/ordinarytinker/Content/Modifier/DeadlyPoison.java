package com.hoshino.ordinarytinker.Content.Modifier;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SpyglassItem;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.logic.ToolEvents;

import java.util.Arrays;
import java.util.List;

public class DeadlyPoison extends Modifier implements  MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        var entity=context.getLivingTarget();
        if (entity != null) {
            entity.getActiveEffectsMap().put(MobEffects.POISON,new MobEffectInstance(MobEffects.POISON,200 * modifier.getLevel(),modifier.getLevel()));
        }
    }
    private final int[] color=new int[]{0xffea95,0xffaaff,0x55c4ff};
}
