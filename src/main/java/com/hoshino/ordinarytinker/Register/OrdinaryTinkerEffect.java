package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Context.MobEffect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerEffect {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final RegistryObject<MobEffect> mercurypoisoning = EFFECT.register("mercurypoisoning", MercuryPoisoning::new);
    public static void register(IEventBus bus){
        EFFECT.register(bus);
    }
}
