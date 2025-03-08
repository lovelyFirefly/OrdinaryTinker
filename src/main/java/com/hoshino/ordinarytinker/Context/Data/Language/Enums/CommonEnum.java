package com.hoshino.ordinarytinker.Context.Data.Language.Enums;

import com.hoshino.ordinarytinker.Context.Data.Tconstruct.OTMaterialID;
import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import net.minecraft.world.item.Item;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public enum CommonEnum {
    ArmorSteel(OrdinaryTinkerItem.ArmorSteel_ingot.get(), "装甲钢锭","ArmorSteel_ingot",OTMaterialID.ArmorSteel.getId(),"装甲钢","ArmorSteel"),
    CheeseAlloy(OrdinaryTinkerItem.CheeseAlloy_ingot.get(), "奶酪合金锭","CheeseAlloy_ingot", OTMaterialID.CheeseAlloy.getId(),"奶酪合金","CheeseAlloy"),
    LeadAmalgamation(OrdinaryTinkerItem.LeadAmalgamation_ingot.get(), "铅汞齐锭","LeadAmalgamation_ingot",OTMaterialID.LeadAmalgamation.getId(),"铅汞齐","LeadAmalgamation");

    private final Item item;
    private final String ItemCn;
    private final String ItemEn;


    CommonEnum(Item item, String ItemCn, String ItemEn, MaterialId MaterialId, String MaterialIDCn, String MaterialIDEn) {
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
