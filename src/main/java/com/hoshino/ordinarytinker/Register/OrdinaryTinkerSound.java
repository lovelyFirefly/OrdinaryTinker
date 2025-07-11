package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class OrdinaryTinkerSound {
    public static final DeferredRegister<SoundEvent> sound=DeferredRegister.create(Registries.SOUND_EVENT, OrdinaryTinker.MODID);

    public static final Supplier<SoundEvent> eagleShootSound=sound.register("eagle_shoot_sound",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("eagle_shoot_sound")));
    public static final Supplier<SoundEvent> starHit=sound.register("star_hit",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("star_hit")));
    public static final Supplier<SoundEvent> beamUp=sound.register("beam_up",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("beam_up")));
    public static final Supplier<SoundEvent> eagle_aim=sound.register("eagle_aim",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("eagle_aim")));
    public static final Supplier<SoundEvent> super_die1=sound.register("super_die1",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("super_die1")));
    public static final Supplier<SoundEvent> super_die2=sound.register("super_die2",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("super_die2")));
    public static final Supplier<SoundEvent> electric_hit=sound.register("electric_hit",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("electric_hit")));
    public static final Supplier<SoundEvent> mika_miss=sound.register("mika_miss",()->SoundEvent.createVariableRangeEvent(OrdinaryTinker.getResource("mika_miss")));
    public static void register(IEventBus bus){
        sound.register(bus);
    }
}
