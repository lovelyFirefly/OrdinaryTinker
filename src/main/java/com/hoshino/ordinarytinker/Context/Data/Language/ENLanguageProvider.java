package com.hoshino.ordinarytinker.Context.Data.Language;

import com.hoshino.ordinarytinker.Context.Data.Language.Enums.FluidEnum;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class ENLanguageProvider extends LanguageProvider {

    public ENLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }
    public String GetFluidlKeyName(String string){
        return "fluid"+"."+MODID+"."+string.replace(MODID+":","");
    }
    public String GetFluidBucketlKeyName(String string){
        return "item"+"."+MODID+"."+string.replace(MODID+":","")+"_bucket";
    }
    @Override
    protected void addTranslations() {
        FluidEnum[] fluidEnums = FluidEnum.values();
        //流体
        for (FluidEnum fluidEnum : fluidEnums) {
            this.add(this.GetFluidlKeyName(fluidEnum.getFluid().getFluidType().toString()), "molten " + fluidEnum.getFluidEn());
            this.add(this.GetFluidBucketlKeyName(fluidEnum.getFluid().getFluidType().toString()), "molten " + fluidEnum.getFluidEn() + "bucket");
        }
    }
}
