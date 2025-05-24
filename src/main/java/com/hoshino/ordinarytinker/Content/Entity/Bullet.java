package com.hoshino.ordinarytinker.Content.Entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;

public class Bullet extends AbstractArrow {
    private final ItemStack tool;
    private final Player owner;

    public Bullet(Level level,ItemStack tool,Player player){
        super(EntityType.ARROW, level);
        this.tool = tool.copy();
        this.owner = player;
    }
    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        ToolAttackUtil.attackEntity(tool,owner,pResult.getEntity());
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }
}
