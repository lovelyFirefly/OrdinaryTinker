package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Context.Modifier.*;
import com.hoshino.ordinarytinker.Context.Modifier.Tool.*;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class OrdinaryTinkerModifier {

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    private static final ModifierDeferredRegister ThermalModifiers = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    public static final StaticModifier<ArmorCoating> Armorcoating = MODIFIERS.register("armorcoating", ArmorCoating::new);
    public static final StaticModifier<Sophisticated> Sophisticated = MODIFIERS.register("sophisticated", Sophisticated::new);
    public static final StaticModifier<HighCa> HighCa = MODIFIERS.register("highca", HighCa::new);
    public static final StaticModifier<MercuryPoisoning> MercuryPoisoning = ThermalModifiers.register("mercurypoisoning", MercuryPoisoning::new);
    //工具专属类词条
    public static final StaticModifier<Riptide> Riptide = MODIFIERS.register("riptide", Riptide::new);   //激流
    public static final StaticModifier<Crcs> Crcs = MODIFIERS.register("crcs", Crcs::new);   //风雨无阻
    public static final StaticModifier<LightningBolt> LightningBolt = MODIFIERS.register("lightningbolt", LightningBolt::new);//引雷
    public static final StaticModifier<Loyal> Loyal = MODIFIERS.register("loyal", Loyal::new);   //忠诚
    public static final StaticModifier<SeaBless> Seabless = MODIFIERS.register("seabless", SeaBless::new);   //还神赐福
    public static final StaticModifier<Crawl> Crawl = MODIFIERS.register("crawl", Crawl::new);   //抓取

    public static void register(IEventBus bus){
        MODIFIERS.register(bus);
    }
}
