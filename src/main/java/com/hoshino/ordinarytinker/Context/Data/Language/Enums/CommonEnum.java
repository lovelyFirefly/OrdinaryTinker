package com.hoshino.ordinarytinker.Context.Data.Language.Enums;

import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import net.minecraft.world.item.Item;

public enum CommonEnum {
    ArmorSteel(OrdinaryTinkerItem.ArmorSteel_ingot.get(), "装甲钢锭","ArmorSteel_ingot"),
    CheeseAlloy(OrdinaryTinkerItem.CheeseAlloy_ingot.get(), "奶酪合金锭","CheeseAlloy_ingot"),
    LeadAmalgamation(OrdinaryTinkerItem.LeadAmalgamation_ingot.get(), "铅汞齐锭","LeadAmalgamation_ingot");

    private final Item item;
    private final String ItemCn;
    private final String ItemEn;


    CommonEnum(Item item, String ItemCn, String ItemEn) {
        //物品
        this.item=item;
        this.ItemCn=ItemCn;
        this.ItemEn=ItemEn;

    }
    //物品
    public Item GetItem(){
        return this.item;
    }
    public String GetItemCn(){
        return this.ItemCn;
    }
    public String GetItemEn(){
        return this.ItemEn;
    }

}
