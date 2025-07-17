package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.hoshino.ordinarytinker.Content.Util.ToolDataNBTCache;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerSound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.UUID;

public class Rapier extends ModifiableItem {
    public Rapier(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
        if (target.isAlive()) {
            var view = ToolStack.from(playerIn.getMainHandItem());
            ModDataNBT data = view.getPersistentData();
            data.putString(ToolDataNBTCache.waltzTarget, target.getStringUUID());
            data.putInt(ToolDataNBTCache.waltzTick, 40);
            var sight = playerIn.getViewVector(1).reverse().scale(1.5f);
            playerIn.setDeltaMovement(new Vec3(sight.x(), 0, sight.z()));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!(entityIn instanceof Player player)) return;
        if (!isSelected) return;

        var tool= ToolStack.from(stack);

        var data = tool.getPersistentData();
        var tick = data.getInt(ToolDataNBTCache.waltzTick);
        var attackTime = data.getInt(ToolDataNBTCache.waltzAttackTime);
        var string = data.getString(ToolDataNBTCache.waltzTarget);
        boolean shouldSet = data.getBoolean(ToolDataNBTCache.waltzSet);
        float angle = data.getFloat(ToolDataNBTCache.waltzAngle);
        SoundEvent event;
        boolean c = player.level().random.nextBoolean();
        if (c) {
            event = SoundEvents.ENDERMAN_TELEPORT;
        } else event = SoundEvents.CHORUS_FRUIT_TELEPORT;
        if (shouldSet) {
            data.putBoolean(ToolDataNBTCache.waltzSet, false);
            player.setYRot(angle);
            player.swing(InteractionHand.MAIN_HAND);
        }
        if (attackTime > 3) {
            player.heal(player.getMaxHealth() * 0.05f * attackTime);
            this.clearData(data);
            return;
        }
        if (worldIn instanceof ServerLevel serverLevel) {
            if (string.isEmpty()) return;
            var target = serverLevel.getEntities().get(UUID.fromString(string));
            if (target instanceof LivingEntity living) {
                if (player.getMainHandItem().isEmpty()) return;
                data.putInt(ToolDataNBTCache.waltzTick, tick - 1);
                if (!target.isAlive()) {
                    player.heal(player.getMaxHealth() * 0.05f * attackTime);
                    this.clearData(data);
                    return;
                }
                switch (tick) {
                    case 33 -> {
                        data.putBoolean(ToolDataNBTCache.waltzSet, true);
                        data.putFloat(ToolDataNBTCache.waltzAngle, 135);
                    }
                    case 32 -> {
                        player.teleportTo(living.getX() + 5, living.getY(), living.getZ() + 5);
                        runToolAttack(player, event, living, serverLevel, tool);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), OrdinaryTinkerSound.beamUp.get(), SoundSource.AMBIENT, 0.5f, 0.8f);
                    }
                    case 25 -> {
                        data.putBoolean(ToolDataNBTCache.waltzSet, true);
                        data.putFloat(ToolDataNBTCache.waltzAngle, 225);
                    }
                    case 24 -> {
                        player.teleportTo(living.getX() - 5, living.getY(), living.getZ() + 5);
                        runToolAttack(player, event, living, serverLevel, tool);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), OrdinaryTinkerSound.beamUp.get(), SoundSource.AMBIENT, 0.5f, 1.0f);
                    }

                    case 17 -> {
                        data.putBoolean(ToolDataNBTCache.waltzSet, true);
                        data.putFloat(ToolDataNBTCache.waltzAngle, 315);
                    }
                    case 16 -> {
                        player.teleportTo(living.getX() - 5, living.getY(), living.getZ() - 5);
                        runToolAttack(player, event, living, serverLevel, tool);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), OrdinaryTinkerSound.beamUp.get(), SoundSource.AMBIENT, 0.5f, 1.2f);
                    }

                    case 9 -> {
                        data.putBoolean(ToolDataNBTCache.waltzSet, true);
                        data.putFloat(ToolDataNBTCache.waltzAngle, 45);
                    }
                    case 8 -> {
                        player.teleportTo(living.getX() + 5, living.getY(), living.getZ() - 5);
                        runToolAttack(player, event, living, serverLevel, tool);
                        serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, target.getX(), target.getY() + target.getBbHeight(), target.getZ(), 40, 0.25, 0.5, 0.25, 0.25);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), OrdinaryTinkerSound.beamUp.get(), SoundSource.AMBIENT, 0.5f, 1.5f);
                    }
                }
            }
        }
    }
    private void clearData(ModDataNBT data) {
        data.remove(ToolDataNBTCache.waltzAttackTime);
        data.remove(ToolDataNBTCache.waltzTick);
        data.remove(ToolDataNBTCache.waltzTarget);
        data.remove(ToolDataNBTCache.waltzAngle);
        data.remove(ToolDataNBTCache.waltzSet);
    }
    private void runToolAttack(Player player, SoundEvent event, LivingEntity target, ServerLevel level, IToolStackView tool) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), event, SoundSource.AMBIENT, 1, 1);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.AMBIENT, 1, 1);
        level.sendParticles(ParticleTypes.CRIT, target.getX(), target.getY(0.5), target.getZ(), 10, 0.1, 0, 0.1, 0.2);
        level.sendParticles(ParticleTypes.PORTAL, player.getX(), player.getY(0.5), player.getZ(), 40, 0.2, 0, 0.2, 0.4);
        target.invulnerableTime = 0;
        var attackTime = tool.getPersistentData().getInt(ToolDataNBTCache.waltzAttackTime);
        tool.getPersistentData().putInt(ToolDataNBTCache.waltzAttackTime, attackTime + 1);
        if (target.isAlive()) {
            ToolAttackUtil.attackEntity(tool, player, InteractionHand.MAIN_HAND, target, () -> 1.0, false);
        }
    }
}
