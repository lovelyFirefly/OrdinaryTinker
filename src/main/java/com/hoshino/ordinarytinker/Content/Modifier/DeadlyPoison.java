package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Content.Entity.FallenStar;
import com.hoshino.ordinarytinker.Content.Particle.ParticleType.StarFallParticleType;
import com.hoshino.ordinarytinker.Content.Util.MobEffectUtil;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class DeadlyPoison extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        var entity = context.getLivingTarget();
        if (entity != null) {
            MobEffectUtil.directAddMobEffect(entity,new MobEffectInstance(MobEffects.POISON,1000,1));
        }
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null) {
            if (attacker instanceof ServerPlayer player) {
                var level = player.serverLevel();
                var tool = ToolStack.from(player.getOffhandItem());
                var fallenStar = new FallenStar(player.level(), target.position(), 3, player);
                level.playSound(null, player.getOnPos(), OrdinaryTinkerSound.eagleShootSound.get(), SoundSource.AMBIENT, 0.5f, 1);
                level.addFreshEntity(fallenStar);
            }
        }
        return false;
    }

    @Override
    public void onProjectileHitBlock(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, BlockHitResult hit, @Nullable LivingEntity attacker) {

    }

    private final int[] color = new int[]{0xffea95, 0xffaaff, 0x55c4ff};
}
