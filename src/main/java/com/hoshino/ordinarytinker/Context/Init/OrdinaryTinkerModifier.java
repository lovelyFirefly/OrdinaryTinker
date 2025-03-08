package com.hoshino.ordinarytinker.Context.Init;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class OrdinaryTinkerModifier {
    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    public OrdinaryTinkerModifier(){
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
