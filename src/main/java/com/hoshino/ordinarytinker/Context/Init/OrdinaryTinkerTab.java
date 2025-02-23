package com.hoshino.ordinarytinker.Context.Init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerTab {
    public static final DeferredRegister<CreativeModeTab> creative_mode_tab=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);
    public static final String OrdinaryTinkerTab="ordinarytinker.materials";
    public static final Supplier<CreativeModeTab> materials=creative_mode_tab.register("materials",()-> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //物品栏名称
            .title(Component.translatable(OrdinaryTinkerTab))
            //图标
            .icon(()-> Items.GLASS.getDefaultInstance())
            .displayItems((itemDisplayParameters, output) -> {
                //这个物品栏当中包含的物品
            })
            .build()
    );
}
