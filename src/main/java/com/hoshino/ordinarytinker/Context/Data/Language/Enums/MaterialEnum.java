package com.hoshino.ordinarytinker.Context.Data.Language.Enums;

import com.hoshino.ordinarytinker.Context.Data.Tconstruct.OTMaterialID;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public enum MaterialEnum {
    ArmorSteel(OTMaterialID.ArmorSteel.getId(),"装甲钢","ArmorSteel","在原版的钢中掺杂了部分金属元素获得了性能更加优秀的合金","In the original steel, some metal elements were mixed to obtain an alloy with better performance","性能优秀却不沉重","its great but not heavy"),
    CheeseAlloy(OTMaterialID.CheeseAlloy.getId(),"奶酪合金","CheeseAlloy","没想到吧，这玩意也可以合金","You can't believe it, this thing can also be alloyed","由奶与爱组成","Composed of milk and love"),
    LeadAmalgamation(OTMaterialID.LeadAmalgamation.getId(),"铅汞齐","LeadAmalgamation","研究表明铅元素对末影有抑制作用","Studies have shown that lead has an inhibitory effect on endumer","这个可好吃了","this tastes good!");

//format(,"","","","","","");

    private final MaterialId MaterialId;
    private final String MaterialIDCn;
    private final String MaterialIDEn;
    private final String EncyclopediaCn;
    private final String EncyclopediaEn;
    private final String FlavorCn;
    private final String FlavorEn;

    MaterialEnum(MaterialId MaterialId,String MaterialIDCn,String MaterialIDEn,String EncyclopediaCn,String EncyclopediaEn,String FlavorCn,String FlavorEn){
        //材料
        this.MaterialId=MaterialId;
        this.MaterialIDCn = MaterialIDCn;
        this.MaterialIDEn = MaterialIDEn;
        this.EncyclopediaCn=EncyclopediaCn;
        this.EncyclopediaEn=EncyclopediaEn;
        this.FlavorCn=FlavorCn;
        this.FlavorEn=FlavorEn;
    }
    //材料
    public MaterialId GetMaterialId(){
        return this.MaterialId;
    }
    public String GetMaterialIdCn(){
        return this.MaterialIDCn;
    }
    public String GetMaterialIdEn(){
        return this.MaterialIDEn;
    }
    public String GetEncyclopediaCn(){
        return this.EncyclopediaCn;
    }
    public String GetEncyclopediaEn(){
        return this.EncyclopediaEn;
    }
    public String GetFlavorCn(){
        return this.FlavorCn;
    }
    public String GetFlavorEn(){
        return this.FlavorEn;
    }
}
