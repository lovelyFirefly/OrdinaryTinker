package com.hoshino.ordinarytinker.Content.Entity.Goal;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class FearGoal extends Goal {
    private final Mob mob;
    private final float detectionRange;
    private final double speedModifier;

    public FearGoal(Mob mob, float range, double speed) {
        this.mob = mob;
        this.detectionRange = range;
        this.speedModifier = speed;
    }

    @Override
    public boolean canUse() {
        Player player = mob.level().getNearestPlayer(mob, detectionRange);
        if(player==null)return false;
        return isFearSource(player);
    }
    private boolean isFearSource(Player player) {
        return ModifierLevel.EquipHasModifierlevel(player, OrdinaryTinkerModifier.hoshinoHaloStaticModifier.getId());
    }

    @Override
    public void start() {
        Player target = mob.level().getNearestPlayer(mob, detectionRange);
        if (target == null) return;
        Vec3 fleePos = calculateFleePosition(target.position());
        mob.getNavigation().moveTo(mob.getNavigation().createPath(BlockPos.containing(fleePos), 1), speedModifier);
        mob.setTarget(null);
        mob.setAggressive(false);
    }
    private Vec3 calculateFleePosition(Vec3 sourcePos) {
        Mob mob = this.mob;
        Vec3 fleeDirection = mob.position().subtract(sourcePos).normalize();
        int maxAttempts = 5;
        for (int i = 0; i < maxAttempts; i++) {
            double offsetX = fleeDirection.x * 8 + (mob.getRandom().nextDouble() - 0.5) * 4;
            double offsetZ = fleeDirection.z * 8 + (mob.getRandom().nextDouble() - 0.5) * 4;
            BlockPos targetPos = mob.blockPosition().offset((int) offsetX, 0, (int) offsetZ);
            Path path = mob.getNavigation().createPath(targetPos, 1);
            if (path != null && path.canReach()) {
                return Vec3.atBottomCenterOf(targetPos);
            }
        }
        return mob.position().add(fleeDirection.scale(8));
    }
}