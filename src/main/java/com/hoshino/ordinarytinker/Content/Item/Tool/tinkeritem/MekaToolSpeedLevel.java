package com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem;

import com.hoshino.ordinarytinker.Config.OrdinaryTinkerConfig;

public enum MekaToolSpeedLevel {
    SLOW(OrdinaryTinkerConfig.MekaToolSlowSpeed.get()),
    MEDIUM(OrdinaryTinkerConfig.MekaToolMediumSpeed.get()),
    HIGH(OrdinaryTinkerConfig.MekaToolHighSpeed.get()),
    EXTREME(OrdinaryTinkerConfig.MekaToolExtremeSpeed.get());
    private final double speed;

    MekaToolSpeedLevel(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
