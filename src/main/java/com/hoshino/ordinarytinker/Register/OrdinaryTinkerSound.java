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
    public static void register(IEventBus bus){
        sound.register(bus);
    }
}
