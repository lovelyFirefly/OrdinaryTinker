package com.hoshino.ordinarytinker.Content.Modifier.Combat;

import com.hoshino.ordinarytinker.Content.Util.ToolDataNBTCache;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
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
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EntityInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.UUID;

public class Nkssdtt extends Modifier implements InventoryTickModifierHook, EntityInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ENTITY_INTERACT);
    }

    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!(holder instanceof Player player)) return;
        if (!isSelected) return;
        var data = tool.getPersistentData();
        var time = player.getPersistentData().getInt("nksswait");
        var string = data.getString(ToolDataNBTCache.nkssdttTarget);
        if(time>1){
            player.getPersistentData().putInt("nksswait",time-1);
            return;
        } else if (time==1) {
            player.setDeltaMovement(0,-2,0);
            player.getPersistentData().putInt("nksswait",0);
            return;
        }
        if (string.isEmpty()) return;
        var uuid = UUID.fromString(string);
        if (world instanceof ServerLevel level) {
            var entity = level.getEntities().get(uuid);
            if (entity instanceof LivingEntity lv) {
                if (lv.isAlive()) {
                    entity.hurt(OrdinaryTinkerDamageTypes.source(level,OrdinaryTinkerDamageTypes.PlayerSoulgeAttack,player),10);
                    SoundEvent events;
                    boolean c=player.level().random.nextBoolean();
                    if(c){
                        events=OrdinaryTinkerSound.super_die1.get();
                    }
                    else events=OrdinaryTinkerSound.super_die2.get();
                    player.level().playSound(null,entity.getX(),entity.getY(),entity.getZ(), events, SoundSource.AMBIENT,1,1);
                    player.level().playSound(null,entity.getX(),entity.getY(),entity.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.AMBIENT,1,1);
                    level.sendParticles(ParticleTypes.CRIT, entity.getX(), entity.getY(0.5), entity.getZ(), 10, 0.1, 0, 0.1, 0.2);
                    level.sendParticles(ParticleTypes.DAMAGE_INDICATOR, entity.getX(), entity.getY(0.5), entity.getZ(), 20, 0.1, 0, 0.1, 0.2);
                    data.remove(ToolDataNBTCache.nkssdttTarget);
                    player.swing(InteractionHand.MAIN_HAND,true);
                } else data.remove(ToolDataNBTCache.nkssdttTarget);
            }
        }
    }

    @Override
    public @NotNull InteractionResult beforeEntityUse(IToolStackView tool, ModifierEntry modifier, Player player, Entity target, InteractionHand hand, InteractionSource source) {
        if (target instanceof LivingEntity living) {
            player.setDeltaMovement(new Vec3(0, 2, 0));
            var view = ToolStack.from(player.getMainHandItem());
            ModDataNBT data = view.getPersistentData();
            data.putString(ToolDataNBTCache.nkssdttTarget, living.getStringUUID());
            player.getPersistentData().putInt("nksswait", 6);
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

}
