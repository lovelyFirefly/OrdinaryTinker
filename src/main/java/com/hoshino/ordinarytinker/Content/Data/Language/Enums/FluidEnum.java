package com.hoshino.ordinarytinker.Content.Data.Language.Enums;


import com.hoshino.ordinarytinker.Register.OrdinaryTinkerFluid;
import net.minecraft.world.level.material.Fluid;

public enum FluidEnum {
    molten_armorsteel(OrdinaryTinkerFluid.molten_armorsteel.get(), "装甲钢", "armorsteel"),
    molten_leadamalgamation(OrdinaryTinkerFluid.molten_leadamalgamation.get(), "铅汞齐", "leadamalgamation");
    private final Fluid Fluid;
    private final String FluidCn;
    private final String FluidEn;

    FluidEnum(Fluid Fluid, String FluidCn, String FluidEn) {
        this.Fluid = Fluid;
        this.FluidCn = FluidCn;
        this.FluidEn = FluidEn;
    }

    public Fluid getFluid() {
        return this.Fluid;
    }

    public String getFluidCn() {
        return this.FluidCn;
    }

    public String getFluidEn() {
        return this.FluidEn;
    }
}
