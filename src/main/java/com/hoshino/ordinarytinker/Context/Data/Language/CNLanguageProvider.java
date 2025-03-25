package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.CommonEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.FluidEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.MaterialEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.ModifierEnum;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class CNLanguageProvider extends LanguageProvider {
    public CNLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }
    //生成材料描述键名
    public String GetMaterialKeyName(String string){
        return "material"+"."+MODID+"."+string.replace(MODID+":","");
    }
    public String GetModifierlKeyName(String string){
        return "modifier"+"."+MODID+"."+string.replace(MODID+":","");
    }
    public String GetFluidlKeyName(String string){
        return "fluid"+"."+MODID+"."+string.replace(MODID+":","");
    }
    public String GetFluidBucketlKeyName(String string){
        return "item"+"."+MODID+"."+string.replace(MODID+":","")+"_bucket";
    }
    @Override
    protected void addTranslations() {
        CommonEnum[] commonEnum = CommonEnum.values();
        MaterialEnum[] materialEnums = MaterialEnum.values();
        ModifierEnum[] modifierEnums = ModifierEnum.values();
        FluidEnum[] fluidEnums = FluidEnum.values();
        //流体
        for(int i = 0; i< commonEnum.length; i++){
            this.add(commonEnum[i].GetItem(), commonEnum[i].GetItemCn());

            this.add(this.GetMaterialKeyName(materialEnums[i].GetMaterialId().toString()), materialEnums[i].GetMaterialIdCn());
            this.add(this.GetMaterialKeyName(materialEnums[i].GetMaterialId().toString())+".encyclopedia", materialEnums[i].GetEncyclopediaCn());
            this.add(this.GetMaterialKeyName(materialEnums[i].GetMaterialId().toString())+".flavor", materialEnums[i].GetFlavorCn());

            this.add(this.GetModifierlKeyName(modifierEnums[i].GetModifierId().toString()), modifierEnums[i].GetModifierCn());
            this.add(this.GetModifierlKeyName(modifierEnums[i].GetModifierId().toString())+".flavor", modifierEnums[i].GetFlavorCn());
            this.add(this.GetModifierlKeyName(modifierEnums[i].GetModifierId().toString())+".description", modifierEnums[i].GetDescriptionCn());
        }
        for (FluidEnum fluidEnum : fluidEnums) {
            this.add(this.GetFluidlKeyName(fluidEnum.GetFluid().getFluidType().toString()), "熔融" + fluidEnum.GetFluidCn());
            this.add(this.GetFluidBucketlKeyName(fluidEnum.GetFluid().getFluidType().toString()), "熔融" + fluidEnum.GetFluidCn() + "桶");
        }
    }
}
