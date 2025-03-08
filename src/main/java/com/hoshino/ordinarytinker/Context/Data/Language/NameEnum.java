package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import net.minecraft.world.item.Item;

public enum NameEnum {
    ArmorSteel(OrdinaryTinkerItem.ArmorSteel_ingot.get(), "装甲钢锭","ArmorSteel"),
    CheeseAlloy(OrdinaryTinkerItem.CheeseAlloy_ingot.get(), "奶酪合金锭","CheeseAlloy"),
    aws(OrdinaryTinkerItem.LeadAmalgamation_ingot.get(), "铅汞齐锭","LeadAmalgamation_ingot");

    public final Item item;
    public final String Cn;
    public final String En;
    NameEnum(Item item, String Cn,String En) {
        this.item=item;
        this.Cn=Cn;
        this.En=En;
    }

    public Item GetItem(){
        return this.item;
    }
    public String GetCn(){
        return this.Cn;
    }
    public String GetEn(){
        return this.En;
    }
}
