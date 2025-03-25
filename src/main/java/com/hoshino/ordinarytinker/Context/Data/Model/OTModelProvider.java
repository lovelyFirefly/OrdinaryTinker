package com.hoshino.ordinarytinker.Context.Data.Model;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.CommonEnum;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OTModelProvider extends ItemModelProvider {
    public OTModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        CommonEnum[] commonEnum = CommonEnum.values();
        for (CommonEnum anEnum : commonEnum) {
            this.basicItem(anEnum.GetItem());
        }
    }
}
