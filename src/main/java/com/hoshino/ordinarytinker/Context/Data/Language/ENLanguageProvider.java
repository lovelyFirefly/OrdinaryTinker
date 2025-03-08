package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.CommonEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.MaterialEnum;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class ENLanguageProvider extends LanguageProvider {

    public ENLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }
    public String GetMaterialKeyName(String string){
        return "material"+"."+MODID+"."+string.replace(MODID+":","");
    }
    @Override
    protected void addTranslations() {
        CommonEnum[] commonEnum = CommonEnum.values();
        MaterialEnum[] materialEnums = MaterialEnum.values();
        //物品
        List<Item> items = new ArrayList<>();
        List<String> ItemEn = new ArrayList<>();
        //材料
        List<MaterialId> materials = new ArrayList<>();
        List<String> materialsEn = new ArrayList<>();
        List<String> encyclopediaEn = new ArrayList<>();
        List<String> flavorEn = new ArrayList<>();
        for(int i = 0; i< commonEnum.length; i++){
            items.add(Arrays.stream(commonEnum).toList().get(i).GetItem());
            ItemEn.add(Arrays.stream(commonEnum).toList().get(i).GetItemEn());
            materials.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialId());
            materialsEn.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialIdEn());
            encyclopediaEn.add(Arrays.stream(materialEnums).toList().get(i).GetEncyclopediaEn());
            flavorEn.add(Arrays.stream(materialEnums).toList().get(i).GetFlavorEn());
            this.add(items.get(i), ItemEn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString()), materialsEn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".encyclopedia", encyclopediaEn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".flavor", flavorEn.get(i));
        }
    }
}
