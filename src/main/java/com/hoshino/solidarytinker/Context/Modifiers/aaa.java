package com.hoshino.solidarytinker.Context.Modifiers;


import com.hoshino.solidarytinker.Context.DamageType.STDamageSource;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class aaa extends Modifier implements MeleeDamageModifierHook {

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if(context.getLivingTarget()!=null){
            context.getLivingTarget().kill();
            context.getLivingTarget().hurt(STDamageSource.source(context.getLevel(),STDamageSource.mercurypoisoning, context.getAttacker(), context.getAttacker()),1000);
        }
        return baseDamage;
    }
}
