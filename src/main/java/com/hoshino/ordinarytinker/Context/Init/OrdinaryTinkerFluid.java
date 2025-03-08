package com.hoshino.ordinarytinker.Context.Init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerFluid {
    public static final DeferredRegister<Fluid> ITEM=DeferredRegister.create(Registries.FLUID,MODID);

    protected static Map<FluidObject<ForgeFlowingFluid>,Boolean> FLUID_MAP = new HashMap<>();

    public static Set<FluidObject<ForgeFlowingFluid>> getFluids(){
        return FLUID_MAP.keySet();
    }
}
