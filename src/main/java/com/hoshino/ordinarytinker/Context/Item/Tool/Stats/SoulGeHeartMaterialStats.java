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
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public record SoulGeHeartMaterialStats(float detection_range, float exert_times, float attack_frequency, float kill_threshold) implements IMaterialStats {
    public static final MaterialStatsId ID = new MaterialStatsId("ordinarytinker", "soulge_heart");
    public static final MaterialStatType<SoulGeHeartMaterialStats> TYPE= new MaterialStatType<>(ID,new SoulGeHeartMaterialStats(1,1,1,0), RecordLoadable.create(
            FloatLoadable.ANY.defaultField("detection_range", 0.0F, true, SoulGeHeartMaterialStats::detection_range),
            FloatLoadable.ANY.defaultField("exert_times", 0.0F, true, SoulGeHeartMaterialStats::exert_times),
            FloatLoadable.ANY.defaultField("attack_frequency", 0.0F, true, SoulGeHeartMaterialStats::attack_frequency),
            FloatLoadable.ANY.defaultField("kill_threshold", 0.0F, true, SoulGeHeartMaterialStats::kill_threshold),
            SoulGeHeartMaterialStats::new));
    private static final String DETECTION_RANGE =IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("detection_range"));
    private static final String EXERT_TIMES=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("exert_times"));
    private static final String ATTACK_FREQUENCY=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("attack_frequency"));
    private static final String KILL_THRESHOLD=IMaterialStats.makeTooltipKey(OrdinaryTinker.getResource("kill_threshold"));
    private static final List<Component> DESCRIPTION = ImmutableList.of(
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("soulge_heart.detection_range.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("soulge_heart.exert_times.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("soulge_heart.attack_frequency.description")),
            IMaterialStats.makeTooltip(OrdinaryTinker.getResource("soulge_heart.kill_threshold.description")));
    public SoulGeHeartMaterialStats(float detection_range, float exert_times, float attack_frequency, float kill_threshold) {
        this.detection_range=detection_range;
        this.exert_times=exert_times;
        this.attack_frequency=attack_frequency;
        this.kill_threshold=kill_threshold;
    }

    @Override
    public @NotNull MaterialStatType<?> getType() {
        return TYPE;
    }
    @Override
    public @NotNull List<Component> getLocalizedInfo() {
        List<Component> info = Lists.newArrayList();
        info.add(IToolStat.formatColoredBonus(DETECTION_RANGE, this.detection_range));
        info.add(IToolStat.formatColoredBonus(EXERT_TIMES, this.exert_times));
        info.add(IToolStat.formatColoredBonus(ATTACK_FREQUENCY, this.attack_frequency));
        info.add(IToolStat.formatColoredBonus(KILL_THRESHOLD, this.kill_threshold));
        return info;
    }
    public float getDetectionRange() {
        return this.detection_range;
    }
    public float getExertTimes(){
        return this.exert_times;
    }
    public float getAttackFrequency(){
        return this.attack_frequency;
    }
    public float getKillThreshold(){
        return this.kill_threshold;
    }
    @Override
    public @NotNull List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(@NotNull ModifierStatsBuilder builder, float scale) {
        OrdinaryTinkerToolStat.DETECTION_RANGE.update(builder, this.detection_range);
        OrdinaryTinkerToolStat.EXERT_TIMES.update(builder, this.exert_times);
        OrdinaryTinkerToolStat.ATTACK_FREQUENCY.update(builder, this.attack_frequency);
        OrdinaryTinkerToolStat.KILLTHRESHOLD.update(builder, this.kill_threshold);
    }
}
