package com.hoshino.ordinarytinker.Content.Modifier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Test extends Modifier implements  MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        var entity=context.getLivingTarget();
        if (entity != null) {
            entity.getActiveEffectsMap().put(MobEffects.POISON,new MobEffectInstance(MobEffects.POISON,100,1));
        }
    }
}
