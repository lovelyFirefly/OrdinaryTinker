package com.hoshino.ordinarytinker.Content.Particle;

import lombok.Getter;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

@Getter
public class StarFallParticle extends TextureSheetParticle {
    private final Vec3 origin;
    private final float speed;
    private final float maxRadius;
    private float currentRadius;
    public StarFallParticle(ClientLevel pLevel, double pX, double pY, double pZ, int alpha,int color, float speed, float size,float maxRadius,Vec3 origin) {
        super(pLevel, pX, pY, pZ);
        this.lifetime=100;
        float r = (color >> 16) & 0xFF;
        float g = (color >> 8) & 0xFF;
        float b = color & 0xFF;
        this.setColor(r,g,b);
        this.setAlpha(alpha);
        this.hasPhysics=false;
        this.quadSize=0.5f * size;
        this.origin=origin;
        this.speed=speed;
        this.maxRadius=maxRadius;
        this.currentRadius=0;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age < this.lifetime) {
            currentRadius = Math.min(
                    maxRadius,
                    currentRadius + speed
            );
            Vec3 dir = new Vec3(
                    this.x - origin.x,
                    this.y - origin.y,
                    this.z - origin.z
            ).normalize();
            this.move(
                    dir.x * speed,
                    dir.y * speed,
                    dir.z * speed
            );
            this.quadSize = 0.1f * (1 - (currentRadius / maxRadius));
            float alpha = 1 - (age / (float) lifetime);
            this.setAlpha(alpha);
        } else {
            this.remove();
        }
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

}
