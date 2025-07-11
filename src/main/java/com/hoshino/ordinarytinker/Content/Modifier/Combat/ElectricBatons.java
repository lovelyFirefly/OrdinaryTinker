package com.hoshino.ordinarytinker.Content.Modifier.Combat;

import com.hoshino.ordinarytinker.Content.Modifier.Armor.ArmorCoating;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EntityInteractionModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElectricBatons extends Modifier implements MeleeHitModifierHook, EntityInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.ENTITY_INTERACT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        var random = new Random();
        LivingEntity target = context.getLivingTarget();
        if (!(target instanceof Mob mob) || !(context.getAttacker() instanceof ServerPlayer player)) return;
        ServerLevel level = player.serverLevel();
        Mob currentTarget = mob;
        List<Mob> hasBeenAttackedMob = new ArrayList<>();
        hasBeenAttackedMob.add(mob);
        player.level().playSound(null, player.getOnPos(), OrdinaryTinkerSound.electric_hit.get(), SoundSource.AMBIENT, 1, 1);
        for (int i = 0; i < 8; i++) {
            Mob nextMob = getNearestMob(currentTarget, hasBeenAttackedMob, 20);
            if (nextMob == null) break;
            nextMob.hurt(OrdinaryTinkerDamageTypes.source(level, OrdinaryTinkerDamageTypes.PlayerSoulgeAttack, player), 10);
            drawParticleBeam(currentTarget, nextMob, random, level);
            currentTarget = nextMob;
            hasBeenAttackedMob.add(nextMob);
        }
    }

    private void drawParticleBeam(Mob mob1, Mob mob2, Random random, ServerLevel level) {
        double d0 = mob2.getX() - mob1.getX();
        double d1 = mob2.getY() + (double) (mob2.getBbHeight() * 0.5F)
                - (mob1.getY() + (double) mob1.getEyeHeight() * 0.5D);
        double d2 = mob2.getZ() - mob1.getZ();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        d0 = d0 / d3;
        d1 = d1 / d3;
        d2 = d2 / d3;
        double d4 = random.nextDouble();
        while (d4 < d3) {
            d4 += 0.3D;
            level.sendParticles(ParticleTypes.FIREWORK, mob1.getX() + d0 * d4, mob1.getY() + d1 * d4 + (double) mob1.getEyeHeight() * 0.5D, mob1.getZ() + d2 * d4, 2, 0.0D, 0.0D, 0.0D, 0);
        }
    }

    private Mob getNearestMob(Mob mob1, List<Mob> beenAttackedMob, double range) {
        AABB aabb = new AABB(mob1.getOnPos()).inflate(range);
        var list = mob1.level().getEntitiesOfClass(Mob.class, aabb, entity -> entity != mob1);
        Mob nearestMob = null;
        double currentRange = Double.MAX_VALUE;
        for (Mob mob : list) {
            if (beenAttackedMob.contains(mob)) continue;
            if (mob.distanceTo(mob1) <= currentRange) {
                currentRange = mob.distanceTo(mob1);
                nearestMob = mob;
            }
        }
        return nearestMob;
    }
}
