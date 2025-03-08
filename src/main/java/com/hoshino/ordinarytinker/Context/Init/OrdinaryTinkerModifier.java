package com.hoshino.ordinarytinker.Context.Init;

import com.hoshino.ordinarytinker.Context.Modifiers.*;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;


public class OrdinaryTinkerModifier {

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OrdinaryTinker.MODID);
    public static final StaticModifier<ArmorCoating> armorcoating = MODIFIERS.register("armorcoating", ArmorCoating::new);
    public static final StaticModifier<Sophisticated> sophisticated = MODIFIERS.register("sophisticated", Sophisticated::new);
    public static final StaticModifier<HighCa> HighCa = MODIFIERS.register("highca", HighCa::new);
    public static final StaticModifier<MercuryPoisoning> MercuryPoisoning = MODIFIERS.register("mercurypoisoning", MercuryPoisoning::new);

    public static void register(IEventBus bus){
        MODIFIERS.register(bus);
    }
}
