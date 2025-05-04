package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;


import com.c2h6s.etstlib.util.DynamicComponentUtil;
import com.hoshino.ordinarytinker.Network.OTChannel;
import com.hoshino.ordinarytinker.Network.Packet.SoulGeAttackPacket;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerToolStat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SoulGe extends ModifiableItem {
    public SoulGe(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    private final Random rand = new Random();

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level worldIn, Player playerIn, @NotNull InteractionHand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1 || hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public @NotNull List<Component> getStatInformation(@NotNull IToolStackView tool, @Nullable Player player, @NotNull List<Component> tooltips, @NotNull TooltipKey key, @NotNull TooltipFlag tooltipFlag) {
        int[] color = new int[]{0xffea95, 0xffaaff, 0x55c4ff};
        TooltipBuilder builder = new TooltipBuilder(tool, tooltips);
        if (tool.hasTag(TinkerTags.Items.DURABILITY)) {
            builder.addDurability();
        }
        if (tool.hasTag(TinkerTags.Items.MELEE)) {
            builder.add(ToolStats.ATTACK_DAMAGE);
            builder.add(ToolStats.ATTACK_SPEED);
        }
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.detection_range",
                ":" + String.format("%d", tool.getStats().get(OrdinaryTinkerToolStat.DETECTION_RANGE).intValue()), color, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.exert_times",
                ":" + String.format("%d", tool.getStats().get(OrdinaryTinkerToolStat.EXERT_TIMES).intValue()), color, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.attack_frequency",
                ":" + String.format("%d", tool.getStats().get(OrdinaryTinkerToolStat.ATTACK_FREQUENCY).intValue()), color, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.kill_threshold",
                ":" + String.format("%d", Math.round(tool.getStats().get(OrdinaryTinkerToolStat.KILLTHRESHOLD) * 100)) + "%", color, 20, 20, true));
        builder.addAllFreeSlots();
        for (ModifierEntry entry : tool.getModifierList()) {
            entry.getHook(ModifierHooks.TOOLTIP).addTooltip(tool, entry, player, tooltips, key, tooltipFlag);
        }
        return tooltips;
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity attacker, @NotNull ItemStack stack, int timeLeft) {
        IToolStackView tool = ToolStack.from(stack);
        var level = attacker.level();
        int dist = Math.round(tool.getStats().get(OrdinaryTinkerToolStat.DETECTION_RANGE));
        int attackFrequency = Math.round(tool.getStats().get(OrdinaryTinkerToolStat.ATTACK_FREQUENCY));
        int exertTimes = Math.round(tool.getStats().get(OrdinaryTinkerToolStat.EXERT_TIMES));
        var pointedEntity = this.getPointedEntity(attacker, level, dist);
        if (pointedEntity != null && pointedEntity.isAlive()) {
            var targetedTimes = pointedEntity.getPersistentData().getInt("targeted");
            if (targetedTimes < exertTimes * 3) {
                pointedEntity.getPersistentData().putInt("targeted", Math.min(exertTimes * 3, exertTimes + targetedTimes));
            }

        }
        if (attacker.tickCount % attackFrequency == 0) {
            this.attackTargets(attacker, tool, dist);
        }
    }

    public LivingEntity getPointedEntity(LivingEntity attacker, Level level, int dist) {
        Vec3 playerEyePosition = attacker.getEyePosition(1F);
        Vec3 playerLook = attacker.getViewVector(1F);
        Vec3 Vector3d2 = playerEyePosition.add(playerLook.x * dist, playerLook.y * dist, playerLook.z * dist);
        LivingEntity pointedEntity = null;
        List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, attacker.getBoundingBox().expandTowards(playerLook.x * dist, playerLook.y * dist, playerLook.z * dist).inflate(1.0F, 1.0F, 1.0F));
        double d2 = dist;
        for (LivingEntity nearbyEntity : nearbyEntities) {
            AABB axisAlignedBB = nearbyEntity.getBoundingBox().inflate(nearbyEntity.getPickRadius());
            Optional<Vec3> optional = axisAlignedBB.clip(playerEyePosition, Vector3d2);
            if (axisAlignedBB.contains(playerEyePosition)) {
                if (d2 >= (double) 0.0F) {
                    pointedEntity = nearbyEntity;
                    d2 = 0.0F;
                }
            } else if (optional.isPresent()) {
                double d3 = playerEyePosition.distanceTo(optional.get());
                if (d3 < d2 || d2 == (double) 0.0F) {
                    if (nearbyEntity.getRootVehicle() == attacker.getRootVehicle() && !attacker.canRiderInteract()) {
                        if (d2 == (double) 0.0F) {
                            pointedEntity = nearbyEntity;
                        }
                    } else {
                        pointedEntity = nearbyEntity;
                    }
                    return pointedEntity;
                }
            }
        }
        return null;
    }

    private void drawParticleBeam(LivingEntity player, LivingEntity target) {
        double d0 = target.getX() - player.getX();
        double d1 = target.getY() + (double) (target.getBbHeight() * 0.5F)
                - (player.getY() + (double) player.getEyeHeight() * 0.5D);
        double d2 = target.getZ() - player.getZ();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        d0 = d0 / d3;
        d1 = d1 / d3;
        d2 = d2 / d3;
        double d4 = this.rand.nextDouble();
        while (d4 < d3) {
            d4 += 1.0D;
            player.level().addParticle(ParticleTypes.SOUL, player.getX() + d0 * d4, player.getY() + d1 * d4 + (double) player.getEyeHeight() * 0.5D, player.getZ() + d2 * d4, 0.0D, 0.0D, 0.0D);
        }
    }

    private void attackTargets(LivingEntity entity, IToolStackView tool, int radius) {
        if (!(entity instanceof Player player)) return;
        float killThreshold = tool.getStats().get(OrdinaryTinkerToolStat.KILLTHRESHOLD);
        List<Mob> mobs = player.level().getEntitiesOfClass(Mob.class, new AABB(player.blockPosition()).inflate(radius));
        mobs.stream().filter(mob -> mob.getPersistentData().contains("targeted")).limit(10).forEach(mob -> {
            var tag = mob.getPersistentData();
            int mark = tag.getInt("targeted");
            if (mark <= 0 || tag.contains("ready_to_die")) return;
            mob.invulnerableTime = 0;
            ToolAttackUtil.attackEntity(tool, player, mob);
            ToolDamageUtil.damageAnimated(tool, 1, player);
            tag.putInt("targeted", --mark);
            if (mob.level().isClientSide()) {
                OTChannel.SendToServer(new SoulGeAttackPacket(mob.getUUID(), mark));
            }
            if (mob.getHealth() < mob.getMaxHealth() * killThreshold && mob.isAlive()) {
                if (ModifierUtil.getModifierLevel(player.getMainHandItem(), OrdinaryTinkerModifier.crawlStaticModifier.getId()) > 0) {
                    Vec3 delta = mob.position().subtract(player.position());
                    if (delta.length() >= 4) {
                        mob.moveTo(player.position().add(delta.normalize().scale(4)));
                    }
                }
                mob.getActiveEffects().clear();
                mob.setDeltaMovement(new Vec3(0, 2.5, 0));
                mob.setNoGravity(false);
                tag.putInt("ready_to_die", 9);
            }
            drawParticleBeam(player, mob);
            if (!mob.isAlive()) tag.remove("targeted");
        });
    }
}
