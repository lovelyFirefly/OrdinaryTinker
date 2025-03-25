package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.CommonEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.MaterialEnum;
import com.hoshino.ordinarytinker.Context.Data.Language.Enums.ModifierEnum;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierId;

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
        List<String> ItemEn = new ArrayList<>();
        //材料
        List<MaterialId> materials = new ArrayList<>();
        List<String> materialsEn = new ArrayList<>();
        List<String> encyclopediaEn = new ArrayList<>();
        List<String> flavorEn = new ArrayList<>();
        //词条
        List<ModifierId> ModifierId = new ArrayList<>();
        List<String> ModifierEn = new ArrayList<>();
        List<String> ModifierFlavorEn = new ArrayList<>();
        List<String> DescriptionEn = new ArrayList<>();
        for(int i = 0; i< commonEnum.length; i++){
            items.add(Arrays.stream(commonEnum).toList().get(i).GetItem());
            ItemEn.add(Arrays.stream(commonEnum).toList().get(i).GetItemEn());

            materials.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialId());
            materialsEn.add(Arrays.stream(materialEnums).toList().get(i).GetMaterialIdEn());
            encyclopediaEn.add(Arrays.stream(materialEnums).toList().get(i).GetEncyclopediaEn());
            flavorEn.add(Arrays.stream(materialEnums).toList().get(i).GetFlavorEn());

            ModifierId.add(Arrays.stream(modifierEnums).toList().get(i).GetModifierId());
            ModifierEn.add(Arrays.stream(modifierEnums).toList().get(i).GetModifierEn());
            ModifierFlavorEn.add(Arrays.stream(modifierEnums).toList().get(i).GetFlavorEn());
            DescriptionEn.add(Arrays.stream(modifierEnums).toList().get(i).GetDescriptionEn());

            this.add(items.get(i), ItemEn.get(i));

            this.add(this.GetMaterialKeyName(materials.get(i).toString()), materialsEn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".encyclopedia", encyclopediaEn.get(i));
            this.add(this.GetMaterialKeyName(materials.get(i).toString())+".flavor", flavorEn.get(i));

            this.add(this.GetModifierlKeyName(ModifierId.get(i).toString()), ModifierEn.get(i));
            this.add(this.GetModifierlKeyName(ModifierId.get(i).toString())+".flavor", ModifierFlavorEn.get(i));
            this.add(this.GetModifierlKeyName(ModifierId.get(i).toString())+".description", DescriptionEn.get(i));
        }
    }
}
