package com.hoshino.ordinarytinker.Context.Data.Model;

import com.hoshino.ordinarytinker.Context.Data.Language.LanguageEnum;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class OTModelProvider extends ItemModelProvider {
    public OTModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        LanguageEnum[] languageEnum = LanguageEnum.values();
        List<Item> items=new ArrayList<>();
        for (LanguageEnum anEnum : languageEnum) {
            items.add(anEnum.GetItem());
        }
        for(Item item : items){
            this.basicItem(item);
        }
    }
}
