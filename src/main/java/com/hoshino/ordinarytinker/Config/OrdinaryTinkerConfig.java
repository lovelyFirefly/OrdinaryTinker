package com.hoshino.ordinarytinker.Config;
import net.minecraftforge.common.ForgeConfigSpec;

public class OrdinaryTinkerConfig {
//    public static final ForgeConfigSpec.Builder MaterialBuilder = new ForgeConfigSpec.Builder()
//            .comment("材料词条部分的配置")//块上面的注解
//            .push("材料词条");//块配置名称
    public static final ForgeConfigSpec.Builder ToolBuilder = new ForgeConfigSpec.Builder()
            .comment("工具部分的配置")//块上面的注解
            .push("模组工具");//块配置名称
    //匠魂开拓者工具不同模式模式对应的挖掘速度
    public static final ForgeConfigSpec.DoubleValue MekaToolSlowSpeed = ToolBuilder.comment("匠魂开拓者工具低速模式对应的挖掘速度,默认0.5")
            .defineInRange("MekaToolSlowSpeed", 0.5, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue MekaToolMediumSpeed = ToolBuilder.comment("匠魂开拓者工具中速模式对应的挖掘速度,默认1.0")
            .defineInRange("MekaToolMediumSpeed", 1.0, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue MekaToolHighSpeed = ToolBuilder.comment("匠魂开拓者工具高速模式对应的挖掘速度倍率,默认2.0")
            .defineInRange("MekaToolHighSpeed", 2.0, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue MekaToolExtremeSpeed = ToolBuilder.comment("匠魂开拓者工具极速模式对应的挖掘速度,默认5.0")
            .defineInRange("MekaToolExtremeSpeed", 5.0, 0, Integer.MAX_VALUE);
    //三叉戟相关
    public static final ForgeConfigSpec.IntValue TridentLoyalSpeed = ToolBuilder.comment("三叉戟忠诚收回的速度倍率,默认5")
            .defineInRange("TridentLoyalSpeed", 5, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.DoubleValue TridentDamage = ToolBuilder.comment("三叉戟远程伤害倍率,默认0.8")
            .defineInRange("TridentDamage", 0.8D, 0.01, 1000D);
    public static final ForgeConfigSpec.IntValue TridentRipSpeed = ToolBuilder.comment("三叉戟冲刺的速度倍率,默认1 ")
            .defineInRange("TridentRipSpeed", 1, 0, Integer.MAX_VALUE);
    //魂戈相关
    public static final ForgeConfigSpec.BooleanValue inDirectKill = ToolBuilder.comment("魂戈斩杀是否穿魔,默认不穿")
            .define("SoulgeDirectKill",false);
    //流能装甲
    public static final ForgeConfigSpec.BooleanValue isProtectBypassMagic = ToolBuilder.comment("层流护甲是否对穿魔等伤害起效,默认对穿魔无效")
            .define("fluidPlateProtectAll",false);
//  public static final ForgeConfigSpec Materialspec = MaterialBuilder.pop().build();
    public static final ForgeConfigSpec Toolspec = ToolBuilder.pop().build();
}
