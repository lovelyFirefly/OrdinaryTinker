package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Context.Item.Tool.Stats.SoulGeHeartMaterialStats;
import com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem.MekaTool;
import com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem.SoulGe;
import com.hoshino.ordinarytinker.Context.Item.Tool.tinkeritem.Trident;
import com.hoshino.ordinarytinker.Context.Item.Tool.toolDefinitions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerItem {
    public static final ItemDeferredRegisterExtension OTHER_ITEM = new ItemDeferredRegisterExtension(MODID);
    public static final DeferredRegister<Item> ITEM=DeferredRegister.create(Registries.ITEM,MODID);

    private static final Item.Properties TOOL = (new Item.Properties()).stacksTo(1);
    private static final Item.Properties PART = (new Item.Properties()).stacksTo(64);
    private static final Item.Properties CASTS = (new Item.Properties()).stacksTo(64);

    public static List<RegistryObject<Item>> commonItem =new ArrayList<>(List.of());
    public static List<RegistryObject<Item>> toolsAndParts =new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        commonItem.add(object);
        return object;
    }

    public static final Supplier<Item> ArmorSteel_ingot=registerCommonMaterials(ITEM,"armorsteel_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> CheeseAlloy_ingot=registerCommonMaterials(ITEM,"cheesealloy_ingot",()->new Item(new Item.Properties()));
    public static final Supplier<Item> LeadAmalgamation_ingot=registerCommonMaterials(ITEM,"leadamalgamation_ingot",()->new Item(new Item.Properties()));

    public static final ItemObject<ToolPartItem> mining_core = OTHER_ITEM.register("mining_core", () -> new ToolPartItem(PART, StatlessMaterialStats.BINDING.getIdentifier()));
    public static final ItemObject<ToolPartItem> soulge_heart = OTHER_ITEM.register("soulge_heart", () -> new ToolPartItem(PART,SoulGeHeartMaterialStats.ID));
    public static final ItemObject<ToolPartItem> trident_head = OTHER_ITEM.register("trident_head", () -> new ToolPartItem(PART, HeadMaterialStats.ID));

    public static final CastItemObject tridentHeadCast = OTHER_ITEM.registerCast(trident_head, CASTS);

    public static final RegistryObject<ModifiableItem> mekatool = ITEM.register("mekatool", () -> new MekaTool(TOOL, toolDefinitions.MEKATOOL));
    public static final RegistryObject<ModifiableItem> trident = ITEM.register("trident", () -> new Trident(TOOL, toolDefinitions.TRIDENT));
    public static final RegistryObject<ModifiableItem> soulge = ITEM.register("soulge", () -> new SoulGe(TOOL, toolDefinitions.Soulge));
    public OrdinaryTinkerItem(){
    }

    public static void register(IEventBus bus){
        ITEM.register(bus);
        OTHER_ITEM.register(bus);
    }
}
