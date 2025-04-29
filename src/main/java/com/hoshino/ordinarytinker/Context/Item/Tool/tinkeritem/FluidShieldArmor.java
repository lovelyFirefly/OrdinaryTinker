package com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem;

import com.c2h6s.etstlib.util.DynamicComponentUtil;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerToolStat;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
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
    public @NotNull List<Component> getStatInformation(@NotNull IToolStackView tool, @Nullable Player player, @NotNull List<Component> tooltips, @NotNull TooltipKey key, @NotNull TooltipFlag tooltipFlag) {
        int[] color=new int[]{0xffaaff,0xa347ff,0x1eedff};
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
                ":" + String.format("%d", tool.getStats().get(OrdinaryTinkerToolStat.BASE_CONSUMPTION).intValue()),color,20,20,true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.consumption_multiplier",
                ":" + String.format("%.2f", tool.getStats().get(OrdinaryTinkerToolStat.CONSUMPTION_MULTIPLIER)),color,20,20,true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.damage_reduction",
                ":" + String.format("%.2f", tool.getStats().get(OrdinaryTinkerToolStat.DAMAGE_REDUCTION) * 100)+"%",color,20,20,true));
        builder.add(DynamicComponentUtil.ScrollColorfulText.getColorfulText(
                "tool_stat.ordinarytinker.fluid_capability",
                ":" + String.format("%d", Math.round(tool.getStats().get(ToolTankHelper.CAPACITY_STAT))),color,20,20,true));
        builder.addAllFreeSlots();
        for (ModifierEntry entry : tool.getModifierList()) {
            entry.getHook(ModifierHooks.TOOLTIP).addTooltip(tool, entry, player, tooltips, key, tooltipFlag);
        }
        return tooltips;
    }
    public static float getModifyScale(ItemStack stack){
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.DAMAGE_REDUCTION);
    }
    public static float getBaseCost(ItemStack stack){
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.BASE_CONSUMPTION);
    }
    public static float getCostScale(ItemStack stack){
        return ToolStack.from(stack).getStats().get(OrdinaryTinkerToolStat.CONSUMPTION_MULTIPLIER);
    }
    public static float finalCost(ItemStack stack){
        return FluidShieldArmor.getBaseCost(stack) * FluidShieldArmor.getCostScale(stack);
    }
}
