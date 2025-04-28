package com.hoshino.ordinarytinker.Register;

import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerToolStat {
    public static final FloatToolStat DETECTION_RANGE = ToolStats.register(new FloatToolStat(name("detection_range"), 16755455, 0.0F, 1.0F, Integer.MAX_VALUE));
    public static final FloatToolStat EXERT_TIMES = ToolStats.register(new FloatToolStat(name("exert_times"), 16755455, 0.0F, 1.0F, Integer.MAX_VALUE));
    public static final FloatToolStat ATTACK_FREQUENCY = ToolStats.register(new FloatToolStat(name("attack_frequency"), 16755455, 0.0F, 1.0F,  Integer.MAX_VALUE));
    public static final FloatToolStat KILLTHRESHOLD = ToolStats.register(new FloatToolStat(name("kill_threshold"), 16755455, 0.0F, 0.0F,  Float.MAX_VALUE));
    public static final FloatToolStat RESIST_MULTIPLIER = ToolStats.register(new FloatToolStat(name("resist_multiplier"), 16755455, 0.0F, 0.0F,  Float.MAX_VALUE));
    public static final FloatToolStat CONSUMPTION_MULTIPLIER = ToolStats.register(new FloatToolStat(name("consumption_multiplier"), 16755455, 0.0F, 0.0F,  Float.MAX_VALUE));
    public static final FloatToolStat DAMAGE_REDUCTION = ToolStats.register(new FloatToolStat(name("damage_reduction"), 16755455, 0.0F, 0.0F,  Float.MAX_VALUE));

    private static ToolStatId name(String name) {
        return new ToolStatId(MODID, name);
    }
}
