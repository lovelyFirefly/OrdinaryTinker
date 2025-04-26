package com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.hoshino.ordinarytinker.Context.DamageType.OTDamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import slimeknights.mantle.util.OffhandCooldownTracker;
import slimeknights.mantle.util.SingleKeyMultimap;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.definition.module.weapon.MeleeHitToolHook;
import slimeknights.tconstruct.library.tools.helper.ModifierLootingHandler;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.Util;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.DoubleSupplier;

public class SoulgeAttackLogic {
    private static final UUID OFFHAND_DAMAGE_MODIFIER_UUID = UUID.fromString("fd666e50-d2cc-11eb-b8bc-0242ac130003");
    private static final float DEGREE_TO_RADIANS = (float)Math.PI / 180F;
    private static final AttributeModifier ANTI_KNOCKBACK_MODIFIER = new AttributeModifier(TConstruct.MOD_ID + ".anti_knockback", 1f, Operation.ADDITION);

    public static DoubleSupplier getCooldownFunction(Player player, InteractionHand hand) {
        if (hand == InteractionHand.OFF_HAND) {
            return () -> OffhandCooldownTracker.getCooldown(player);
        }
        return () -> player.getAttackStrengthScale(0.5f);
    }
    public static float getAttributeAttackDamage(IToolStackView tool, LivingEntity holder, EquipmentSlot slotType) {
        if (slotType == EquipmentSlot.MAINHAND || holder.level().isClientSide) {
            return (float) holder.getAttributeValue(Attributes.ATTACK_DAMAGE);
        }
        ItemStack mainStack = holder.getMainHandItem();
        Multimap<Attribute,AttributeModifier> mainModifiers = null;
        if (!mainStack.isEmpty()) {
            mainModifiers = new SingleKeyMultimap<>(Attributes.ATTACK_DAMAGE, holder.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE));
        }
        ImmutableList.Builder<AttributeModifier> listBuilder = ImmutableList.builder();
        listBuilder.add(new AttributeModifier(OFFHAND_DAMAGE_MODIFIER_UUID, "tconstruct.tool.offhand_attack_damage", tool.getStats().get(ToolStats.ATTACK_DAMAGE), AttributeModifier.Operation.ADDITION));
        BiConsumer<Attribute, AttributeModifier> attributeConsumer = (attribute, modifier) -> {
            if (attribute == Attributes.ATTACK_DAMAGE) {
                listBuilder.add(modifier);
            }
        };
        for (ModifierEntry entry : tool.getModifierList()) {
            entry.getHook(ModifierHooks.ATTRIBUTES).addAttributes(tool, entry, EquipmentSlot.MAINHAND, attributeConsumer);
        }
        Multimap<Attribute,AttributeModifier> offhandModifiers = new SingleKeyMultimap<>(Attributes.ATTACK_DAMAGE, listBuilder.build());

        AttributeMap modifiers = holder.getAttributes();
        if (mainModifiers != null) modifiers.removeAttributeModifiers(mainModifiers);
        modifiers.addTransientAttributeModifiers(offhandModifiers);
        float damage = (float) holder.getAttributeValue(Attributes.ATTACK_DAMAGE);
        modifiers.removeAttributeModifiers(offhandModifiers);
        if (mainModifiers != null) modifiers.addTransientAttributeModifiers(mainModifiers);
        return damage;
    }
    public static boolean dealDefaultDamage(LivingEntity attacker, Entity target, float damage) {
        if (attacker instanceof Player player) {
            return target.hurt(OTDamageTypes.source(player.level(),OTDamageTypes.PlayerSoulgeAttack,player), damage);
        }
        return target.hurt(attacker.damageSources().mobAttack(attacker), damage);
    }
    public static boolean attackEntity(ItemStack stack, Player attacker, Entity targetEntity) {
        return attackEntity(ToolStack.from(stack), attacker, targetEntity);
    }
    public static boolean attackEntity(IToolStackView tool, Player attacker, Entity targetEntity) {
        return attackEntity(tool, attacker, InteractionHand.MAIN_HAND, targetEntity, getCooldownFunction(attacker, InteractionHand.MAIN_HAND), false);
    }
    public static boolean attackEntity(IToolStackView tool, LivingEntity attackerLiving, InteractionHand hand, Entity targetEntity, DoubleSupplier cooldownFunction, boolean isExtraAttack) {
        return attackEntity(tool, attackerLiving, hand, targetEntity, cooldownFunction, isExtraAttack, Util.getSlotType(hand));
    }
    @Nullable
    public static LivingEntity getLivingEntity(Entity entity) {
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        return entity instanceof LivingEntity living ? living : null;
    }
    public static boolean attackEntity(IToolStackView tool, LivingEntity attackerLiving, InteractionHand hand, Entity targetEntity, DoubleSupplier cooldownFunction, boolean isExtraAttack, EquipmentSlot sourceSlot) {
        if (tool.isBroken() || !tool.hasTag(TinkerTags.Items.MELEE)) {
            return false;
        }
        Level level = attackerLiving.level();
        if (level.isClientSide || !targetEntity.isAttackable() || targetEntity.skipAttackInteraction(attackerLiving)) {
            return true;
        }
        LivingEntity targetLiving = getLivingEntity(targetEntity);
        Player attackerPlayer = null;
        if (attackerLiving instanceof Player player) {
            attackerPlayer = player;
        }
        float damage = getAttributeAttackDamage(tool, attackerLiving, sourceSlot);
        float cooldown = (float)cooldownFunction.getAsDouble();
        boolean fullyCharged = cooldown > 0.9f;
        boolean isCritical = !isExtraAttack && fullyCharged && attackerLiving.fallDistance > 0.0F && !attackerLiving.onGround() && !attackerLiving.onClimbable()
                && !attackerLiving.isInWater() && !attackerLiving.hasEffect(MobEffects.BLINDNESS)
                && !attackerLiving.isPassenger() && targetLiving != null && !attackerLiving.isSprinting();
        ToolAttackContext context = new ToolAttackContext(attackerLiving, attackerPlayer, hand, sourceSlot, targetEntity, targetLiving, isCritical, cooldown, isExtraAttack);
        float baseDamage = damage;
        List<ModifierEntry> modifiers = tool.getModifierList();
        for (ModifierEntry entry : modifiers) {
            damage = entry.getHook(ModifierHooks.MELEE_DAMAGE).getMeleeDamage(tool, entry, context, baseDamage, damage);
        }
        if (damage <= 0) {
            return !isExtraAttack;
        }
        SoundEvent sound;
        if (attackerLiving.isSprinting() && fullyCharged) {
            sound = SoundEvents.PLAYER_ATTACK_KNOCKBACK;
        } else if (fullyCharged) {
            sound = SoundEvents.PLAYER_ATTACK_STRONG;
        } else {
            sound = SoundEvents.PLAYER_ATTACK_WEAK;
        }
        if (!isExtraAttack) {
            float criticalModifier = isCritical ? 1.5f : 1.0f;
            if (attackerPlayer != null) {
                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(attackerPlayer, targetEntity, isCritical, isCritical ? 1.5F : 1.0F);
                isCritical = hitResult != null;
                if (isCritical) {
                    criticalModifier = hitResult.getDamageModifier();
                }
            }
            if (isCritical) {
                damage *= criticalModifier;
            }
        }
        boolean isMagic = damage > baseDamage;
        if (cooldown < 1) {
            damage *= (0.2f + cooldown * cooldown * 0.8f);
        }
        float oldHealth = 0.0F;
        if (targetLiving != null) {
            oldHealth = targetLiving.getHealth();
        }
        ModifierLootingHandler.setLootingSlot(attackerLiving, sourceSlot);
        boolean didHit;
        if (isExtraAttack) {
            didHit = dealDefaultDamage(attackerLiving, targetEntity, damage);
        } else {
            didHit = MeleeHitToolHook.dealDamage(tool, context, damage);
        }
        ModifierLootingHandler.setLootingSlot(attackerLiving, EquipmentSlot.MAINHAND);
        if (!didHit) {
            if (!isExtraAttack) {
                level.playSound(null, attackerLiving.getX(), attackerLiving.getY(), attackerLiving.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, attackerLiving.getSoundSource(), 1.0F, 1.0F);
            }
            for (ModifierEntry entry : modifiers) {
                entry.getHook(ModifierHooks.MELEE_HIT).failedMeleeHit(tool, entry, context, damage);
            }

            return !isExtraAttack;
        }
        float damageDealt = damage;
        if (targetLiving != null) {
            damageDealt = oldHealth - targetLiving.getHealth();
        }
        if (targetEntity.hurtMarked && targetEntity instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(targetEntity));
            targetEntity.hurtMarked = false;
        }
        if (attackerPlayer != null) {
            if (isCritical) {
                sound = SoundEvents.PLAYER_ATTACK_CRIT;
                attackerPlayer.crit(targetEntity);
            }
            if (isMagic) {
                attackerPlayer.magicCrit(targetEntity);
            }
            level.playSound(null, attackerLiving.getX(), attackerLiving.getY(), attackerLiving.getZ(), sound, attackerLiving.getSoundSource(), 1.0F, 1.0F);
        }
        if (damageDealt > 2.0F && level instanceof ServerLevel server) {
            int particleCount = (int)(damageDealt * 0.5f);
            server.sendParticles(ParticleTypes.DAMAGE_INDICATOR, targetEntity.getX(), targetEntity.getY(0.5), targetEntity.getZ(), particleCount, 0.1, 0, 0.1, 0.2);
        }
        attackerLiving.setLastHurtMob(targetEntity);
        if (targetLiving != null) {
            EnchantmentHelper.doPostHurtEffects(targetLiving, attackerLiving);
        }
        for (ModifierEntry entry : modifiers) {
            entry.getHook(ModifierHooks.MELEE_HIT).afterMeleeHit(tool, entry, context, damageDealt);
        }
        float speed = tool.getStats().get(ToolStats.ATTACK_SPEED);
        int time = Math.round(20f / speed);
        if (time < targetEntity.invulnerableTime) {
            targetEntity.invulnerableTime = (targetEntity.invulnerableTime + time) / 2;
        }
        if (attackerPlayer != null) {
            if (targetLiving != null) {
                if (!level.isClientSide && !isExtraAttack) {
                    ItemStack held = attackerLiving.getItemBySlot(sourceSlot);
                    if (!held.isEmpty()) {
                        held.hurtEnemy(targetLiving, attackerPlayer);
                    }
                }
                attackerPlayer.awardStat(Stats.DAMAGE_DEALT, Math.round(damageDealt * 10.0F));
            }
            attackerPlayer.causeFoodExhaustion(0.1F);
            if (!isExtraAttack) {
                attackerPlayer.awardStat(Stats.ITEM_USED.get(tool.getItem()));
            }
        }
        if (!tool.hasTag(TinkerTags.Items.UNARMED)) {
            int durabilityLost = targetLiving != null ? 1 : 0;
            if (!tool.hasTag(TinkerTags.Items.MELEE_PRIMARY)) {
                durabilityLost *= 2;
            }
            ToolDamageUtil.damageAnimated(tool, durabilityLost, attackerLiving);
        }

        return true;
    }
}
