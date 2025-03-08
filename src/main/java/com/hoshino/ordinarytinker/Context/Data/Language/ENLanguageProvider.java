package com.hoshino.ordinarytinker.Context.Data.Language;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.ArrayList;
import java.util.List;

public class ENLanguageProvider extends LanguageProvider {

    public ENLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }
    @Override
    protected void addTranslations() {
        NameEnum[] nameEnum=NameEnum.values();
        List<Item> items=new ArrayList<>();
        List<String>En=new ArrayList<>();
        for (NameEnum anEnum : nameEnum) {
            items.add(anEnum.GetItem());
        }
        for (NameEnum anEnum : nameEnum) {
            En.add(anEnum.GetEn());
        }
        for(int i=0;i<En.size();i++){
            this.add(items.get(i),En.get(i));
        }
    }
}
