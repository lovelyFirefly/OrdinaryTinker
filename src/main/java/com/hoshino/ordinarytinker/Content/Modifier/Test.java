package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Content.Item.Tool.Tier.NewNew;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Test extends Modifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.HARVEST_TIER.update(builder, NewNew.instance);
    }

//    @Override
//    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
//        if (context.getLivingTarget() != null && !context.isCritical()) {
//            if (context.getAttacker() instanceof ServerPlayer player) {
//                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(context.getPlayerAttacker(), context.getLivingTarget(), true, 1.5f);
//                if (hitResult == null) return damage;
//                if (context.getLevel().getServer() != null) {
//                    player.crit(context.getLivingTarget());
//                    player.serverLevel().playSound(null,player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.AMBIENT,1f,1f);
//                    return damage * hitResult.getDamageModifier();
//                }
//            }
//        }
//        return damage;
//    }
}
