package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElectricBatons extends ModifiableItem {
    public ElectricBatons(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
        var random = new Random();
        if (!(target instanceof Mob mob) || !(playerIn instanceof ServerPlayer player)) return InteractionResult.PASS;
        ServerLevel level = player.serverLevel();
        Mob currentTarget = mob;
        List<Mob> hasBeenAttackedMob = new ArrayList<>();
        hasBeenAttackedMob.add(mob);
        var tool = ToolStack.from(stack);
        player.level().playSound(null, player.getOnPos(), OrdinaryTinkerSound.electric_hit.get(), SoundSource.AMBIENT, 1, 1);
        for (int i = 0; i < 8; i++) {
            Mob nextMob = getNearestMob(currentTarget, hasBeenAttackedMob, 20);
            if (nextMob == null) break;
            nextMob.hurt(OrdinaryTinkerDamageTypes.source(level, OrdinaryTinkerDamageTypes.PlayerSoulgeAttack, player), 10);
            ToolAttackUtil.attackEntity(tool, player, InteractionHand.MAIN_HAND, nextMob, () -> 1, false);
            drawParticleBeam(currentTarget, nextMob, random, level);
            currentTarget = nextMob;
            hasBeenAttackedMob.add(nextMob);
        }
        return InteractionResult.SUCCESS;
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
