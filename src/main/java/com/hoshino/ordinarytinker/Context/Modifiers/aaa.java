package com.hoshino.ordinarytinker.Context.Modifiers;


import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;



public class aaa extends Modifier implements MeleeHitModifierHook , MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hook) {
        super.registerHooks(hook);
        hook.addHook(this,ModifierHooks.MELEE_HIT,ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        context.getLivingTarget().addEffect(new MobEffectInstance(MobEffects.REGENERATION,100,1,true,true));
        DamageSource source= OTDamageTypes.source(context.getLevel(),OTDamageTypes.MERCURYPOISONING);
        return baseDamage;
    }
}
