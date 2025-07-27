package com.hoshino.ordinarytinker.Content.Modifier;

import com.hoshino.ordinarytinker.Content.Item.Tool.Tier.NewNew;
import com.hoshino.ordinarytinker.Content.Util.LivingPositionRecord;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerDamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Test extends Modifier implements ToolStatsModifierHook , InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS,ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.HARVEST_TIER.update(builder, NewNew.instance);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(holder instanceof ServerPlayer serverPlayer){
            if(serverPlayer.getItemBySlot(EquipmentSlot.FEET)==stack){
                this.drawParticle(serverPlayer.position(),serverPlayer.serverLevel(),5);
                if(serverPlayer.tickCount%8!=0)return;
                var area=new AABB(serverPlayer.getOnPos()).inflate(5);
                var livingList=serverPlayer.serverLevel().getEntitiesOfClass(LivingEntity.class,area,lv->{
                    if(lv instanceof TamableAnimal animal&&animal.isTame()){
                        return false;
                    }
                    return lv.isAlive()&&!(lv instanceof Villager)&&lv!=serverPlayer;
                });
                for(LivingEntity living:livingList){
                    var source= OrdinaryTinkerDamageTypes.source(serverPlayer.level(),OrdinaryTinkerDamageTypes.MERCURYPOISONING,serverPlayer);
                    living.invulnerableTime=0;
                    living.hurt(source,2);
                }
            }
        }
    }
    public void drawParticle(Vec3 center, ServerLevel serverLevel,double radius){
        for(int i=0;i<36;i++){
            double angle=(2 * Math.PI/36)*i;
            double x=center.x + radius * Math.cos(angle);
            double y=center.y;
            double z=center.z + radius * Math.sin(angle);
            serverLevel.sendParticles(ParticleTypes.FIREWORK,x,y,z,1,0,0.15,0,0);
        }
    }

//    @Override
//    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
//        if (context.getLivingTarget() != null && !context.isCritical()) {
//            if (context.getAttacker() instanceof ServerPlayer player) {
//                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(context.getPlayerAttacker(), context.getLivingTarget(), true, 1.5f);
//                if (hitResult == null) return damage;
//                if (context.getLevel().getServer() != null) {
//                    player.crit(context.getLivingTarget());
//                    player.serverLevel().playSound(null,player.getOnPos(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.AMBIENT,1f,1f);
//                    return damage * hitResult.getDamageModifier();
//                }
//            }
//        }
//        return damage;
//    }

}
