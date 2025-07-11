package com.hoshino.ordinarytinker.Content.Data.Model;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class OrdinaryTinkerModelProvider extends ItemModelProvider {
    public OrdinaryTinkerModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> itemsDeferredRegister : OrdinaryTinkerItem.commonItem) {
            if (itemsDeferredRegister.isPresent()) {
                this.basicItem(itemsDeferredRegister.get());
            }
        }
    }
}
