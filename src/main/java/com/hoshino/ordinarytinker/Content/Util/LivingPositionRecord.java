package com.hoshino.ordinarytinker.Content.Util;

import net.minecraft.world.phys.Vec3;

public interface LivingPositionRecord {
    boolean ordinarytinker$movedinLastTick(double offset);
    Vec3 ordinarytinker$getLastPosition();
}
