package com.hoshino.solidarytinker.Context.Init;

import com.hoshino.solidarytinker.Context.Modifiers.aaa;
import com.hoshino.solidarytinker.Solidarytinker;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class SolidarytinkerModifier {
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(Solidarytinker.MODID);
    public SolidarytinkerModifier(){
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final StaticModifier<aaa> aaaStaticModifier = MODIFIERS.register("aaa", aaa::new);
}
