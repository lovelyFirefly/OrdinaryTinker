package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Modifier.*;
import com.hoshino.ordinarytinker.Content.Modifier.Armor.*;
import com.hoshino.ordinarytinker.Content.Modifier.Halo.*;
import com.hoshino.ordinarytinker.Content.Modifier.Tool.Concomitant.*;
import com.hoshino.ordinarytinker.Content.Modifier.Tool.Exclusive.*;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class OrdinaryTinkerModifier {

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    private static final ModifierDeferredRegister ThermalModifiers = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    public static final StaticModifier<ArmorCoating> armorCoatingStaticModifier = MODIFIERS.register("armorcoating", ArmorCoating::new);
    public static final StaticModifier<Sophisticated> sophisticatedStaticModifier = MODIFIERS.register("sophisticated", Sophisticated::new);
    public static final StaticModifier<MercuryPoisoning> mercuryPoisoningStaticModifier = ThermalModifiers.register("mercurypoisoning", MercuryPoisoning::new);
    //工具专属类词条
    public static final StaticModifier<Riptide> riptideStaticModifier = MODIFIERS.register("riptide", Riptide::new);   //激流
    public static final StaticModifier<Crcs> crcsStaticModifier = MODIFIERS.register("crcs", Crcs::new);   //风雨无阻
    public static final StaticModifier<LightningBolt> lightningBoltStaticModifier = MODIFIERS.register("lightningbolt", LightningBolt::new);//引雷
    public static final StaticModifier<Loyal> loyalStaticModifier = MODIFIERS.register("loyal", Loyal::new);   //忠诚
    public static final StaticModifier<SeaBless> seaBlessStaticModifier = MODIFIERS.register("seabless", SeaBless::new);   //还神赐福
    public static final StaticModifier<Crawl> crawlStaticModifier = MODIFIERS.register("crawl", Crawl::new);   //抓取
    //光环类
    public static final StaticModifier<HoshinoHalo> hoshinoHaloStaticModifier = MODIFIERS.register("hoshinohalo", HoshinoHalo::new);
    public static final StaticModifier<Al1sHalo> al1sHaloStaticModifier = MODIFIERS.register("al1shalo", Al1sHalo::new);
    public static final StaticModifier<AzusaHalo> azusaHaloStaticModifier = MODIFIERS.register("azusahalo", AzusaHalo::new);
    public static final StaticModifier<MariHalo> mariHaloStaticModifier = MODIFIERS.register("marihalo", MariHalo::new);
    public static final StaticModifier<ReisaHalo> reisaHaloStaticModifier = MODIFIERS.register("reisahalo", ReisaHalo::new);
    public static final StaticModifier<NatsuHalo> natsuHaloStaticModifier = MODIFIERS.register("natsuhalo", NatsuHalo::new);

    public static void register(IEventBus bus){
        MODIFIERS.register(bus);
    }
}
