package com.hoshino.ordinarytinker.Content.Entity;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerLivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpecialArrow extends Arrow {

    public SpecialArrow(EntityType<? extends Arrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public SpecialArrow(Level pLevel, LivingEntity pShooter) {
        this(OrdinaryTinkerLivingEntity.special_arrow.get(),pLevel);
        this.setPos(pShooter.getX(),pShooter.getEyeY() - 0.1,pShooter.getZ());
        this.setYRot(pShooter.getYRot());
        this.setXRot(pShooter.getXRot());
        this.shootFromRotation(pShooter,pShooter.getXRot(),pShooter.getYRot(),0,3,1);
        setOwner(pShooter);
        this.pickup = Pickup.CREATIVE_ONLY;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(OrdinaryTinkerItem.special_arrow.get());
    }
}
