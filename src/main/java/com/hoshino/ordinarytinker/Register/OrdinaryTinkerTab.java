package com.hoshino.ordinarytinker.Register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;
import static slimeknights.tconstruct.smeltery.TinkerSmeltery.blankRedSandCast;
import static slimeknights.tconstruct.smeltery.TinkerSmeltery.blankSandCast;

public class OrdinaryTinkerTab {
    public static final DeferredRegister<CreativeModeTab> creative_mode_tab= DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final String OrdinaryTinkerMaterials="tab.ordinarytinker.materials";
    public static final String OrdinaryTinkerTools="tab.ordinarytinker.tools";

    private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
        ToolBuildHandler.addVariants(output, tool.get(), "");
    }

    private static void acceptPart(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }
    private static void accept(CreativeModeTab.Output output, Function<CastItemObject, ItemLike> getter, CastItemObject cast) {
        output.accept(getter.apply(cast));
    }
    private static void acceptTools(Consumer<ItemStack> output, EnumObject<?,? extends IModifiable> tools) {
        tools.forEach(tool -> ToolBuildHandler.addVariants(output, tool, ""));
    }
    private static void addCasts(CreativeModeTab.Output output, Function<CastItemObject,ItemLike> getter) {
        accept(output, getter, OrdinaryTinkerItem.tridentHeadCast);
    }

    private static void addToolItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Objects.requireNonNull(tab);
        Consumer<ItemStack> output = tab::accept;
        acceptTool(output,OrdinaryTinkerItem.trident);
        acceptPart(output,OrdinaryTinkerItem.trident_head);
        acceptTool(output,OrdinaryTinkerItem.mekatool);
        acceptPart(output,OrdinaryTinkerItem.mining_core);
        acceptTool(output,OrdinaryTinkerItem.soulge);
        acceptPart(output,OrdinaryTinkerItem.soulge_heart);
        acceptTools(output,OrdinaryTinkerItem.fluid_plate);
        acceptPart(output,OrdinaryTinkerItem.fluid_escape);
        addCasts(tab, CastItemObject::get);
        addCasts(tab, CastItemObject::getSand);
        addCasts(tab, CastItemObject::getRedSand);
    }
    public static final Supplier<CreativeModeTab> materials=creative_mode_tab.register("materials",()-> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //物品栏名称
            .title(Component.translatable(OrdinaryTinkerMaterials))
            //图标
            .icon(OrdinaryTinkerItem.ArmorSteel_ingot.get()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                for(RegistryObject<Item> itemsDeferredRegister: OrdinaryTinkerItem.commonItem){
                    if(itemsDeferredRegister.isPresent()){
                        output.accept(itemsDeferredRegister.get());
                    }
                }
                //这个物品栏当中包含的物品
            })
            .build()
    );
    //类型不一的几个
    public static final Supplier<CreativeModeTab> toolsAndParts=creative_mode_tab.register("tools_and_parts",()-> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //物品栏名称
            .title(Component.translatable(OrdinaryTinkerTools))
            //图标
            .icon(OrdinaryTinkerItem.tridentHeadCast.get()::getDefaultInstance)
            .displayItems(OrdinaryTinkerTab::addToolItems)
            .build()
    );
    public static void register(IEventBus bus){
        creative_mode_tab.register(bus);
    }
}
