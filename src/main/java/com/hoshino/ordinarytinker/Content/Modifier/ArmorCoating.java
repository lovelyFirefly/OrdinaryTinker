package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Content.Entity.Hajimi;
import net.minecraft.world.entity.EntityType;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ArmorCoating extends Modifier implements MeleeDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        Hajimi hajimi=new Hajimi(EntityType.CAT,context.getAttacker().level());
        context.getAttacker().level().addFreshEntity(hajimi);
        hajimi.moveTo(context.getAttacker().position());
        return damage;
    }
}
