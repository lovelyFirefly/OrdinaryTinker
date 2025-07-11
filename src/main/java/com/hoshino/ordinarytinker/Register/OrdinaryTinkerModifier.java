package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Modifier.*;
import com.hoshino.ordinarytinker.Content.Modifier.Armor.*;
import com.hoshino.ordinarytinker.Content.Modifier.Combat.BladedWaltz;
import com.hoshino.ordinarytinker.Content.Modifier.Combat.ElectricBatons;
import com.hoshino.ordinarytinker.Content.Modifier.Combat.Nkssdtt;
import com.hoshino.ordinarytinker.Content.Modifier.Combat.UncannyValley;
import com.hoshino.ordinarytinker.Content.Modifier.General.Dementors;
import com.hoshino.ordinarytinker.Content.Modifier.General.Fear;
import com.hoshino.ordinarytinker.Content.Modifier.General.FlashOfInspiration;
import com.hoshino.ordinarytinker.Content.Modifier.Halo.*;
import com.hoshino.ordinarytinker.Content.Modifier.Tool.Concomitant.*;
import com.hoshino.ordinarytinker.Content.Modifier.Tool.Exclusive.*;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class OrdinaryTinkerModifier {

    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    private static final ModifierDeferredRegister ThermalModifiers = ModifierDeferredRegister.create(OrdinaryTinker.MODID);

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
    //材料词条
    public static final StaticModifier<Sophisticated> sophisticatedStaticModifier = MODIFIERS.register("sophisticated", Sophisticated::new);
    public static final StaticModifier<ArmorCoating> armorCoatingStaticModifier = MODIFIERS.register("armorcoating", ArmorCoating::new);
    public static final StaticModifier<MercuryPoisoning> mercuryPoisoningStaticModifier = MODIFIERS.register("mercurypoisoning", MercuryPoisoning::new);
    public static final StaticModifier<Dementors> dementorsStaticModifier = MODIFIERS.register("dementors", Dementors::new);
    public static final StaticModifier<Fear> fearStaticModifier = MODIFIERS.register("fear", Fear::new);
    public static final StaticModifier<UncannyValley> uncannyValleyStaticModifier = MODIFIERS.register("uncannyvalley", UncannyValley::new);
    public static final StaticModifier<Disguise> disguiseStaticModifier = MODIFIERS.register("disguise", Disguise::new);
    public static final StaticModifier<PotionMaster> potionMasterStaticModifier = MODIFIERS.register("potionmaster", PotionMaster::new);
    public static final StaticModifier<DeadlyPoison> deadlyPoisonStaticModifier = MODIFIERS.register("deadlypoison", DeadlyPoison::new);
    public static final StaticModifier<FlashOfInspiration> flashOfInspirationStaticModifier = MODIFIERS.register("flashofinspiration", FlashOfInspiration::new);
    public static final StaticModifier<IceBlood> iceBloodStaticModifier = MODIFIERS.register("iceblood", IceBlood::new);
    public static final StaticModifier<IronHeart> ironHeartStaticModifier = MODIFIERS.register("ironheart", IronHeart::new);
    public static final StaticModifier<StarFall> starFallStaticModifier = MODIFIERS.register("starfall", StarFall::new);
    public static final StaticModifier<Covert> covertStaticModifier = MODIFIERS.register("covert", Covert::new);
    public static final StaticModifier<Test> testStaticModifier = MODIFIERS.register("test", Test::new);
    public static final StaticModifier<ElectricBatons> electricBatonsStaticModifier = MODIFIERS.register("electricbatons", ElectricBatons::new);
    public static final StaticModifier<Nkssdtt> nkssdttStaticModifier = MODIFIERS.register("nkssdtt", Nkssdtt::new);
    public static final StaticModifier<BladedWaltz> bladedWaltzStaticModifier = MODIFIERS.register("bladedwaltz", BladedWaltz::new);
    public static final StaticModifier<CatSpeed> catSpeedStaticModifier = MODIFIERS.register("catspeed", CatSpeed::new);
    public static final StaticModifier<MikaMiss> mikaMissStaticModifier = MODIFIERS.register("mikamiss", MikaMiss::new);

    public static void register(IEventBus bus) {
        MODIFIERS.register(bus);
    }
}
