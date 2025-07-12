package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerToolStat;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.MultilayerArmorItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class FluidShieldArmor extends MultilayerArmorItem {
    public FluidShieldArmor(ModifiableArmorMaterial material, Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (player.tickCount % 10 == 0 && player.getRemainingFireTicks() > 10) {
            if (slotIndex > 36) {
                var view = ToolStack.from(stack);
                var ordinaryFluidStack = ToolTankHelper.TANK_HELPER.getFluid(view);
                if (ordinaryFluidStack.getFluid() == Fluids.WATER && ordinaryFluidStack.getAmount() > 10) {
                    player.level().playSound(null, player.getOnPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1f, 1);
                    player.clearFire();
                    var finalFluid = new FluidStack(ordinaryFluidStack.getFluid(), ordinaryFluidStack.getAmount() - 10);
                    if (!level.isClientSide()) {
                        ToolTankHelper.TANK_HELPER.setFluid(view, finalFluid);
                    }
                }
            }
        }
    }

    @Override
    public @NotNull List<Component> getStatInformation(@NotNull IToolStackView tool, @Nullable Player player, @NotNull List<Component> tooltips, @NotNull TooltipKey key, @NotNull TooltipFlag tooltipFlag) {
        int[] entryColor = new int[]{0xffaaff, 0xa347ff, 0x1eedff};
        int[] watercolor = new int[]{0x0000ff, 0x627fff, 0x83deff};
        int[] lavaColor = new int[]{0xffaa00, 0xff7f35, 0xff5c3c};
        int[] errorColor = new int[]{0xed1cff, 0x8a1095, 0x47084d};
        var fluidstack = ToolTankHelper.TANK_HELPER.getFluid(tool);
        TooltipBuilder builder = new TooltipBuilder(tool, tooltips);
        if (tool.hasTag(TinkerTags.Items.DURABILITY)) {
            builder.addDurability();
        }
        if (tool.hasTag(TinkerTags.Items.ARMOR)) {
            builder.add(ToolStats.ARMOR);
            builder.add(ToolStats.ARMOR_TOUGHNESS);
        }
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.base_consumption",
                ":" + String.format("%d", tool.getStats().get(OrdinaryTinkerToolStat.BASE_CONSUMPTION).intValue()), entryColor, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.consumption_multiplier",
                ":" + String.format("%.2f", tool.getStats().get(OrdinaryTinkerToolStat.CONSUMPTION_MULTIPLIER)), entryColor, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.damage_reduction",
                ":" + String.format("%.2f", tool.getStats().get(OrdinaryTinkerToolStat.DAMAGE_REDUCTION) * 100) + "%", entryColor, 20, 20, true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.fluid_capability",
                ":" + String.format("%d", Math.round(tool.getStats().get(ToolTankHelper.CAPACITY_STAT))), entryColor, 20, 20, true));
        builder.add(DynamicComponentUtil.BreathColorfulText.getColorfulText("fluid_plate.tooltip1",
                null, 0xebafff, 20, 3000, true));
        if (fluidstack.getFluid() == Fluids.WATER) {
            builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                    fluidstack.getTranslationKey(), fluidstack.getAmount() + "mB", watercolor, 60, 30, true
            ));
            builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                    "fluid_plate.water.tooltip", null, watercolor, 60, 30, true
            ));
        } else if (fluidstack.getFluid() == Fluids.LAVA) {
            builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                    fluidstack.getTranslationKey(), fluidstack.getAmount() + "mB", lavaColor, 60, 30, true
            ));
            builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                    "fluid_plate.lava.tooltip", null, lavaColor, 60, 30, true
            ));
        } else if (fluidstack.isEmpty()) {
            builder.add(DynamicComponentUtil.BreathColorfulText.getColorfulText("fluid_plate.empty.tooltip",
                    null, 0xebafff, 50, 2000, true));

        } else builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "fluid_plate.ordinary.tooltip", null, errorColor, 3, 80, true
        ));
        builder.addAllFreeSlots();
        for (ModifierEntry entry : tool.getModifierList()) {
            entry.getHook(ModifierHooks.TOOLTIP).addTooltip(tool, entry, player, tooltips, key, tooltipFlag);
        }
        return tooltips;
    }

    public static float getModifyScale(ItemStack stack) {
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.DAMAGE_REDUCTION);
    }

    public static float getBaseCost(ItemStack stack) {
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.BASE_CONSUMPTION);
    }

    public static float getCostScale(ItemStack stack) {
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.CONSUMPTION_MULTIPLIER);
    }

    public static float finalCost(ItemStack stack) {
        return FluidShieldArmor.getBaseCost(stack) * FluidShieldArmor.getCostScale(stack);
    }
}
