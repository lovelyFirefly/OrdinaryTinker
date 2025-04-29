package com.hoshino.ordinarytinker.Context.Item.Tool.Stats;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerToolStat;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import java.util.List;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public record FluidEscapeMaterialStats(float base_consumption,float consumption_multiplier,float damage_reduction,float fluid_capability) implements IMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId(MODID, "fluid_escape");
    public static final MaterialStatType<FluidEscapeMaterialStats> TYPE= new MaterialStatType<>(ID,new FluidEscapeMaterialStats(0,0,0,0), RecordLoadable.create(
            FloatLoadable.ANY.defaultField("base_consumption", 0.0F, true, FluidEscapeMaterialStats::base_consumption),
            FloatLoadable.ANY.defaultField("consumption_multiplier", 0.0F, true, FluidEscapeMaterialStats::consumption_multiplier),
            FloatLoadable.ANY.defaultField("damage_reduction", 0.0F, true, FluidEscapeMaterialStats::damage_reduction),
            FloatLoadable.ANY.defaultField("fluid_capability", 0.0F, true, FluidEscapeMaterialStats::fluid_capability),
            FluidEscapeMaterialStats::new));
    private static final String BASE_CONSUMPTION =IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("base_consumption"));
    private static final String CONSUMPTION_MULTIPLIER=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("consumption_multiplier"));
    private static final String DAMAGE_REDUCTION=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("damage_reduction"));
    private static final String FLUID_CAPABILITY=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("fluid_capability"));
    private static final List<Component> DESCRIPTION = ImmutableList.of(
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("fluid_escape.base_consumption.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("fluid_escape.consumption_multiplier.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("fluid_escape.damage_reduction.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("fluid_escape.fluid_capability.description")));
    public FluidEscapeMaterialStats(float base_consumption,float consumption_multiplier,float damage_reduction,float fluid_capability) {
        this.base_consumption=base_consumption;
        this.consumption_multiplier=consumption_multiplier;
        this.fluid_capability=fluid_capability;
        this.damage_reduction=damage_reduction;
    }

    @Override
    public @NotNull MaterialStatType<?> getType() {
        return TYPE;
    }
    @Override
    public @NotNull List<Component> getLocalizedInfo() {
        List<Component> info = Lists.newArrayList();
        info.add(IToolStat.formatColoredBonus(BASE_CONSUMPTION, this.base_consumption));
        info.add(IToolStat.formatColoredBonus(CONSUMPTION_MULTIPLIER, this.consumption_multiplier));
        info.add(IToolStat.formatColoredBonus(DAMAGE_REDUCTION, this.damage_reduction));
        info.add(IToolStat.formatColoredBonus(FLUID_CAPABILITY, this.fluid_capability));
        return info;
    }
    public float getBase_Consumption() {
        return this.base_consumption;
    }
    public float getConsumption_Multiplier(){
        return this.consumption_multiplier;
    }
    public float getDamage_Reduction(){
        return this.damage_reduction;
    }
    public float getFluid_Capability(){
        return this.fluid_capability;
    }
    @Override
    public @NotNull List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(@NotNull ModifierStatsBuilder builder, float scale) {
        OrdinaryTinkerToolStat.BASE_CONSUMPTION.update(builder, this.base_consumption);
        OrdinaryTinkerToolStat.CONSUMPTION_MULTIPLIER.update(builder, this.consumption_multiplier);
        OrdinaryTinkerToolStat.DAMAGE_REDUCTION.update(builder, this.damage_reduction);
        ToolTankHelper.CAPACITY_STAT.update(builder,this.fluid_capability);
    }
}
