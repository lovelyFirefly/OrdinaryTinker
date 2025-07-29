package com.hoshino.ordinarytinker.Content.Data.Tconstruct;

import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.FluidEscapeMaterialStats;
import com.hoshino.ordinarytinker.Content.Item.Tool.Stats.SoulGeHeartMaterialStats;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerMaterialIDs;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.NETHERITE;

@Deprecated
public class OrdinaryTinkerMaterialStatsData extends AbstractMaterialStatsDataProvider {
    public OrdinaryTinkerMaterialStatsData(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addArmor();
        addWeapon();
        addRanged();
    }

    private void addRanged() {
        addMaterialStats(OrdinaryTinkerMaterialIDs.armorSteel.getId(),
                new LimbMaterialStats(350, 0.3f, 0.05f, 0.1f),
                new GripMaterialStats(0.1f, 0.1f, 2f)
        );
    }

    private void addWeapon() {
        addMaterialStats(OrdinaryTinkerMaterialIDs.armorSteel.getId(),
                new HeadMaterialStats(1442, 7.5f, NETHERITE, 3.5f),
                HandleMaterialStats.multipliers().durability(1.35f).miningSpeed(1.2f).attackSpeed(1.1f).attackDamage(1.2f).build(),
                new SoulGeHeartMaterialStats(15, 10, 6, 0.12f),
                StatlessMaterialStats.BINDING);
    }

    @Override
    public @NotNull String getName() {
        return "OrdinaryTinker's MaterialsStats";
    }

    private void addArmor() {
        addArmorShieldStats(OrdinaryTinkerMaterialIDs.armorSteel,
                PlatingMaterialStats.builder().durabilityFactor(350).armor(2, 4, 5, 2).knockbackResistance(0.1f).toughness(3),
                new FluidEscapeMaterialStats(4, 5, 3, 2),
                StatlessMaterialStats.MAILLE);
        addMaterialStats(OrdinaryTinkerMaterialIDs.soulCheese,
                StatlessMaterialStats.SHIELD_CORE,
                StatlessMaterialStats.MAILLE);
    }
}
