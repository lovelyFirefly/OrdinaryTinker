package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerEffect;
import net.minecraft.world.effect.MobEffectInstance;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MercuryPoisoning extends Modifier implements MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (context.getLivingTarget() != null) {
            context.getLivingTarget().addEffect(new MobEffectInstance(OrdinaryTinkerEffect.mercurypoisoning.get(), 100, 1, true, true));
        }
        return damage;
    }
}
