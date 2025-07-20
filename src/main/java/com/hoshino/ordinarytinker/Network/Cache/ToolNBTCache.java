package com.hoshino.ordinarytinker.Network.Cache;

import lombok.Getter;
import lombok.Setter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ToolNBTCache {
    @Setter
    @Getter
    public static int damage;
}
