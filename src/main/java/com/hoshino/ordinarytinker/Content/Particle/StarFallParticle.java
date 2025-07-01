package com.hoshino.ordinarytinker.Content.Particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import lombok.Getter;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
public class StarFallParticle extends TextureSheetParticle {
    private final Vec3 origin;
    private final float speed;
    private final float maxRadius;
    private final float currentRadius;
    public StarFallParticle(ClientLevel pLevel, double pX, double pY, double pZ, int alpha,int color, float speed, float size,float maxRadius,Vec3 origin) {
        super(pLevel, pX, pY, pZ);
        this.lifetime=30;
        float r = (color >> 16) & 0xFF;
        float g = (color >> 8) & 0xFF;
        float b = color & 0xFF;
        this.setColor(r/255f,g/255f,b/255f);
        this.hasPhysics=false;
        this.origin=origin;
        this.speed=speed;
        this.maxRadius=maxRadius;
        this.currentRadius=0;
        this.quadSize=size;
    }

    @Override
    public void tick() {
        super.tick();
        this.quadSize= this.quadSize * 1.15f;
    }

    @Override
    public void render(@NotNull VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        Vec3 vec3 = pRenderInfo.getPosition();
        float f = (float)(Mth.lerp(pPartialTicks, this.xo, this.x) - vec3.x());
        float f1 = (float)(Mth.lerp(pPartialTicks, this.yo, this.y) - vec3.y());
        float f2 = (float)(Mth.lerp(pPartialTicks, this.zo, this.z) - vec3.z());
        Quaternionf quaternionf = new Quaternionf();
        quaternionf.rotateY((float) Math.toRadians(90));
        if (this.roll != 0.0F) {
            Quaternionf rollQuaternion = new Quaternionf();
            rollQuaternion.rotateZ(Mth.lerp(pPartialTicks, this.oRoll, this.roll));
            quaternionf.mul(rollQuaternion);
        }
        Vector3f[] avector3f = new Vector3f[]{
                new Vector3f(-1.0F, 0.0F, -1.0F),
                new Vector3f(-1.0F, 0.0F, 1.0F),
                new Vector3f(1.0F, 0.0F, 1.0F),
                new Vector3f(1.0F, 0.0F, -1.0F)
        };
        float f3 = this.getQuadSize(pPartialTicks);
        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.rotate(quaternionf);
            vector3f.mul(f3);
            vector3f.add(f, f1, f2);
        }
        float f6 = this.getU0();
        float f7 = this.getU1();
        float f4 = this.getV0();
        float f5 = this.getV1();
        int j = this.getLightColor(pPartialTicks);
        addVertex(pBuffer, avector3f[0], f7, f5, j);
        addVertex(pBuffer, avector3f[1], f7, f4, j);
        addVertex(pBuffer, avector3f[2], f6, f4, j);
        addVertex(pBuffer, avector3f[3], f6, f5, j);
        addVertex(pBuffer, avector3f[3], f6, f5, j);
        addVertex(pBuffer, avector3f[2], f6, f4, j);
        addVertex(pBuffer, avector3f[1], f7, f4, j);
        addVertex(pBuffer, avector3f[0], f7, f5, j);
    }

    private void addVertex(VertexConsumer buffer, Vector3f pos, float u, float v, int light) {
        buffer.vertex(pos.x(), pos.y(), pos.z())
                .uv(u, v)
                .color(this.rCol, this.gCol, this.bCol, this.alpha)
                .uv2(light)
                .endVertex();
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }
}
