package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.CommonEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.MaterialEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.ModifierEnum;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;

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
    @Override
    protected void addTranslations() {
        CommonEnum[] commonEnum = CommonEnum.values();
        MaterialEnum[] materialEnums = MaterialEnum.values();
        ModifierEnum[] modifierEnums = ModifierEnum.values();
        //物品
        List<Item> items = new ArrayList<>();
        List<String> ItemCn = new ArrayList<>();
        //材料
        List<MaterialId> materials = new ArrayList<>();
        List<String> materialsCn = new ArrayList<>();
        List<String> encyclopediaCn = new ArrayList<>();
        List<String> flavorCn = new ArrayList<>();
        //词条
        List<Modifier> Modifier = new ArrayList<>();
        List<String> ModifierCn = new ArrayList<>();
        List<String> ModifierFlavorCn = new ArrayList<>();
        List<String> DescriptionCn = new ArrayList<>();
        for(int i = 0; i< commonEnum.length; i++){
            items.add(Arrays.stream(commonEnum).toList().get(i).GetItem());
            ItemCn.add(Arrays.stream(commonEnum).toList().get(i).GetItemCn());

            materials.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialId());
            materialsCn.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialIdCn());
            encyclopediaCn.add(Arrays.stream(materialEnums).toList().get(i).GetEncyclopediaCn());
            flavorCn.add(Arrays.stream(materialEnums).toList().get(i).GetFlavorCn());

            Modifier.add(Arrays.stream(modifierEnums).toList().get(i).GetModifier());
            ModifierCn.add(Arrays.stream(modifierEnums).toList().get(i).GetModifierCn());
            ModifierFlavorCn.add(Arrays.stream(modifierEnums).toList().get(i).GetFlavorCn());
            DescriptionCn.add(Arrays.stream(modifierEnums).toList().get(i).GetDescriptionCn());

            this.add(items.get(i), ItemCn.get(i));

            this.add(this.GetMaterialKeyName(materials.get(i).toString()), materialsCn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".encyclopedia", encyclopediaCn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".flavor", flavorCn.get(i));

            this.add(this.GetModifierlKeyName(Modifier.get(i).toString()), ModifierCn.get(i));
            this.add(this.GetModifierlKeyName(Modifier.get(i).toString())+".flavor", ModifierFlavorCn.get(i));
            this.add(this.GetModifierlKeyName(Modifier.get(i).toString())+".description", DescriptionCn.get(i));
        }
    }
}
