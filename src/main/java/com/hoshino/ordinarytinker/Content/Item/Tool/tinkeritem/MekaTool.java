package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class MekaTool extends ModifiableItem {
    private static final ResourceLocation SPEED = OrdinaryTinker.getResource("speedlevel");

    public int getToolLevel(ItemStack stack) {
        ModDataNBT nbt = ToolStack.from(stack).getPersistentData();
        return nbt.getInt(SPEED);
    }

    public void setToolLevel(int level, ItemStack stack) {
        ToolStack.from(stack).getPersistentData().putInt(SPEED, level);
    }
    public MekaTool(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState blockState, BlockPos pos, LivingEntity entity) {
        return true;
    }
    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        var view=ToolStack.from(stack);
        int speed=view.getStats().getInt(ToolStats.MINING_SPEED);
        var toolSpeedLevels=MekaToolSpeedLevel.values();
        var nbt = view.getPersistentData();
        int toolLevel = nbt.getInt(SPEED);
        return (int) Math.min(Math.round(toolSpeedLevels[toolLevel].getSpeed() * speed),2048);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return true;
    }
}
