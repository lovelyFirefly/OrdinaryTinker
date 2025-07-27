package com.hoshino.ordinarytinker.Register;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

import java.util.HashMap;
import java.util.Map;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;
import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;

public class OrdinaryTinkerFluid {

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MODID);

    private static FluidType.Properties hot(String name, int Temp) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(Temp)
                .descriptionId("fluid." + MODID + "." + name)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }

    protected static Map<FluidObject<ForgeFlowingFluid>, Boolean> FLUID_MAP = new HashMap<>();

    private static FluidObject<ForgeFlowingFluid> registerHotBurning(FluidDeferredRegister register, String name, int temp, int lightLevel, int burnTime, float damage) {
        FluidObject<ForgeFlowingFluid> object = register.register(name).type(hot(name, temp)).bucket().block(createBurning(MapColor.COLOR_GRAY, lightLevel, burnTime, damage)).commonTag().flowing();
        FLUID_MAP.put(object, false);
        return object;
    }

    public static final FluidObject<ForgeFlowingFluid> molten_armorsteel = registerHotBurning(FLUIDS, "molten_armorsteel", 770, 1, 4, 0.5f);
    public static final FluidObject<ForgeFlowingFluid> molten_leadamalgamation = registerHotBurning(FLUIDS, "molten_leadamalgamation", 210, 2, 5, 1f);
    public static final FluidObject<ForgeFlowingFluid> molten_experiencesteel = registerHotBurning(FLUIDS, "molten_experiencesteel", 210, 2, 5, 1f);
    public static final FluidObject<ForgeFlowingFluid> molten_kemomimi = registerHotBurning(FLUIDS, "molten_kemomimi", 210, 2, 5, 1f);
    public static final FluidObject<ForgeFlowingFluid> molten_whitedwarf = registerHotBurning(FLUIDS, "molten_whitedwarf", 210, 2, 5, 1f);
    public static final FluidObject<ForgeFlowingFluid> molten_takeru = registerHotBurning(FLUIDS, "molten_takeru", 210, 2, 5, 1f);
    public static final FluidObject<ForgeFlowingFluid> molten_jailerslime = registerHotBurning(FLUIDS, "molten_jailerslime", 210, 2, 5, 1f);

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
