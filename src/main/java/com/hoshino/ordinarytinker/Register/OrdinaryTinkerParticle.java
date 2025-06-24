package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Particle.ParticleType.StarFallParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final RegistryObject<ParticleType<StarFallParticleType>> STARFALL=PARTICLE.register("star_fall",()->new StarFallParticleType(true,100,0xffaaff,0,1,5,new Vec3(0,-58,0)));
    public static void register(IEventBus bus){
        PARTICLE.register(bus);
    }
}
