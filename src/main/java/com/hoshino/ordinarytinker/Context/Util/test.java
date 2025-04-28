package com.hoshino.ordinarytinker.Context.Util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class test {
    public static void LivingAttackEvent(LivingAttackEvent event){
        var entity=event.getSource().getEntity();
        if(entity instanceof Player player){
            double range=range(player, event.getEntity());
            hurtEntity(range,player);
        }
    }
    public static double range(Player player, LivingEntity target){
        Vec3 playerPos=player.position();
        Vec3 targetPos=target.position();
        Vec3 ac= playerPos.subtract(targetPos);
        return ac.length();
    }
    public static void hurtEntity(double range, LivingEntity lv){
        double x=lv.getX();
        double y=lv.getY();
        double z=lv.getZ();
        var list= lv.level().getEntitiesOfClass(Mob.class, new AABB(x + range, y + range, z + range, x - range, y - range, z - range));
        for(Mob mob:list){
            mob.die(mob.level().damageSources().cramming());
            mob.kill();
            mob.discard();
        }
    }
}
