package com.hoshino.ordinarytinker.Context.Data.Model;

import com.hoshino.ordinarytinker.Context.Data.Language.NameEnum;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelProvider extends ItemModelProvider {
    public ModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        NameEnum[] nameEnum=NameEnum.values();
        List<Item> items=new ArrayList<>();
        for (NameEnum anEnum : nameEnum) {
            items.add(anEnum.GetItem());
        }
        for(Item item : items){
            this.basicItem(item);
        }
    }
}
