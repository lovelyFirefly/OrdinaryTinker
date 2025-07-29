package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.hoshino.ordinarytinker.Content.Entity.Bullet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class TestTool extends ModifiableItem {
    public TestTool(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity entityLiving, ItemStack stack, int timeLeft) {
        if (entityLiving instanceof Player player) {
            ItemStack bulletEquip = entityLiving.getOffhandItem();
            Bullet bullet = new Bullet(pLevel, bulletEquip, player);
        }
    }
}
