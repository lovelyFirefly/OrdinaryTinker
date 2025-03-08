package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Init.OrdinaryTinkerItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.ArrayList;
import java.util.List;

public class CNLanguageProvider extends LanguageProvider {

    public CNLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }
    @Override
    protected void addTranslations() {
        NameEnum[] nameEnum=NameEnum.values();
        List<Item> items=new ArrayList<>();
        List<String>Cn=new ArrayList<>();
        for (NameEnum anEnum : nameEnum) {
            items.add(anEnum.GetItem());
        }
        for (NameEnum anEnum : nameEnum) {
            Cn.add(anEnum.GetCn());
        }
        for(int i=0;i<Cn.size();i++){
            this.add(items.get(i),Cn.get(i));
        }
//        this.add(OrdinaryTinkerItem.ArmorSteel_ingot.get(),"装甲钢锭");
    }
}
