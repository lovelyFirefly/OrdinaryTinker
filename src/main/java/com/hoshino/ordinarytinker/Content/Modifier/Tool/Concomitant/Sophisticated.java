package com.hoshino.ordinarytinker.Content.Modifier.Tool.Concomitant;

import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Sophisticated extends Modifier implements  ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }
    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.DURABILITY.add(builder,400* modifier.getLevel());
        if(context.hasTag(TinkerTags.Items.ARMOR)){
            ToolStats.ARMOR.add(builder, modifier.getLevel());
            ToolStats.ARMOR_TOUGHNESS.add(builder, modifier.getLevel());
        }
        else ToolStats.ATTACK_SPEED.add(builder,0.08 * modifier.getLevel());
    }
}