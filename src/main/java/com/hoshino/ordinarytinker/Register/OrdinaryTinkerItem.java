package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Item.HugeArrowItem;
import com.hoshino.ordinarytinker.Content.Item.SpecialArrowItem;
import com.hoshino.ordinarytinker.Content.Item.Tool.OrdinaryTinkerArmorDefinitions;
import com.hoshino.ordinarytinker.Content.Item.Tool.OrdinaryTinkerToolDefinitions;
import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.FluidEscapeMaterialStats;
import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.SoulGeHeartMaterialStats;
import com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;


public class OrdinaryTinkerItem {
    private static final ItemDeferredRegisterExtension OTHER_ITEM = new ItemDeferredRegisterExtension(MODID);
    private static final ItemDeferredRegisterExtension ModifiableArmor = new ItemDeferredRegisterExtension(MODID);
    private static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, MODID);
    public static final Supplier<Item> star_debris = ITEM.register("star_debris", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> eagle_ammo = ITEM.register("eagle_ammo", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> special_arrow = ITEM.register("special_arrow", () -> new SpecialArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> huge_arrow = ITEM.register("huge_arrow", () -> new HugeArrowItem(new Item.Properties()));
    private static final Item.Properties TOOL = (new Item.Properties()).stacksTo(1);
    public static final RegistryObject<ModifiableItem> mekatool = ITEM.register("mekatool", () -> new MekaTool(TOOL, OrdinaryTinkerToolDefinitions.MEKATOOL));
    public static final RegistryObject<ModifiableItem> trident = ITEM.register("trident", () -> new Trident(TOOL, OrdinaryTinkerToolDefinitions.TRIDENT));
    public static final RegistryObject<ModifiableItem> soulge = ITEM.register("soulge", () -> new SoulGe(TOOL, OrdinaryTinkerToolDefinitions.Soulge));
    public static final RegistryObject<ModifiableItem> electric_baton = ITEM.register("electric_baton", () -> new ElectricBatons(TOOL, OrdinaryTinkerToolDefinitions.electric_baton));
    public static final RegistryObject<ModifiableItem> rapier = ITEM.register("rapier", () -> new Rapier(TOOL, OrdinaryTinkerToolDefinitions.rapier));
    public static final EnumObject<ArmorItem.Type, ModifiableArmorItem> fluid_plate = ModifiableArmor.registerEnum("fluid_plate", ArmorItem.Type.values(), type -> new FluidShieldArmor(OrdinaryTinkerArmorDefinitions.FLUID_PLATE, type, TOOL));
    private static final Item.Properties PART = (new Item.Properties()).stacksTo(64);
    public static final ItemObject<ToolPartItem> mining_core = OTHER_ITEM.register("mining_core", () -> new ToolPartItem(PART, StatlessMaterialStats.BINDING.getIdentifier()));
    public static final ItemObject<ToolPartItem> soulge_heart = OTHER_ITEM.register("soulge_heart", () -> new ToolPartItem(PART, SoulGeHeartMaterialStats.ID));
    public static final ItemObject<ToolPartItem> trident_head = OTHER_ITEM.register("trident_head", () -> new ToolPartItem(PART, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> fluid_escape = OTHER_ITEM.register("fluid_escape", () -> new ToolPartItem(PART, FluidEscapeMaterialStats.ID));
    private static final Item.Properties CASTS = (new Item.Properties()).stacksTo(64);
    public static final CastItemObject tridentHeadCast = OTHER_ITEM.registerCast(trident_head, CASTS);
    public static final CastItemObject fluidEscapeCast = OTHER_ITEM.registerCast(fluid_escape, CASTS);
    public static List<RegistryObject<Item>> commonItem = new ArrayList<>(List.of());
    public static final Supplier<Item> ArmorSteel_ingot = registerCommonMaterials(ITEM, "armorsteel_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> Jailerslime_ingot = registerCommonMaterials(ITEM, "jailerslime_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> SoulcheeseAlloy_ingot = registerCommonMaterials(ITEM, "soulcheese_ingot", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.6F)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 100, 0), 1)
            .build())));
    public static final Supplier<Item> LeadAmalgamation_ingot = registerCommonMaterials(ITEM, "leadamalgamation_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> whitedwarf_ingot = registerCommonMaterials(ITEM, "whitedwarf_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> experiencesteel_ingot = registerCommonMaterials(ITEM, "experiencesteel_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> kemomimi_ingot = registerCommonMaterials(ITEM, "kemomimi_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> takeru_ingot = registerCommonMaterials(ITEM, "takeru_ingot", () -> new Item(new Item.Properties()));

    public OrdinaryTinkerItem() {
    }

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register, String name, Supplier<? extends Item> sup) {
        RegistryObject<Item> object = register.register(name, sup);
        commonItem.add(object);
        return object;
    }

    public static void register(IEventBus bus) {
        ITEM.register(bus);
        OTHER_ITEM.register(bus);
        ModifiableArmor.register(bus);
    }
}
