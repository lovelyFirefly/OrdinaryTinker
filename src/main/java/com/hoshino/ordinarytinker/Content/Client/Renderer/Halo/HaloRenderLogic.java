package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public final class HaloRenderLogic {
    public static void renderHaloIncline(PoseStack poseStack, Player player, float partialTick, ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.5f, 0);
        float yaw = player.getViewYRot(partialTick);
        float pitch = player.getViewXRot(partialTick);
        Quaternionf rotation = new Quaternionf()
                .rotateY(-yaw * ((float) Math.PI / 180F))
                .rotateX(pitch * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;
        float size = 0.4f;
        vertexBuilder.vertex(poseMatrix, -size, 0, -size)
                .color(255, 255, 255, 200)
                .uv(0, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, -size)
                .color(255, 255, 255, 200)
                .uv(1, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, size)
                .color(255, 255, 255, 200)
                .uv(1, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, -size, 0, size)
                .color(255, 255, 255, 200)
                .uv(0, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        poseStack.popPose();
        buffer.endBatch();
    }

    public static void renderHaloHorizontal(PoseStack poseStack, Player player, float partialTick, ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);
        float yaw = player.getViewYRot(partialTick);
        Quaternionf rotation = new Quaternionf().rotateY(-yaw * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;
        float size = 0.4f;
        vertexBuilder.vertex(poseMatrix, -size, 0, -size)
                .color(255, 255, 255, 200)
                .uv(0, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, -size)
                .color(255, 255, 255, 200)
                .uv(1, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, size)
                .color(255, 255, 255, 200)
                .uv(1, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, -size, 0, size)
                .color(255, 255, 255, 200)
                .uv(0, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        poseStack.popPose();
        buffer.endBatch();
    }

    public static void renderDynamicHaloHorizontal(PoseStack poseStack, Player player, float partialTick, ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);
        float yaw = player.getViewYRot(partialTick);
        Quaternionf rotation = new Quaternionf().rotateY(-yaw * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;
        float period = 3.0f;
        float minAlpha = 50.0f;
        float maxAlpha = 200.0f;
        long currentTime = System.currentTimeMillis();
        float phase = (currentTime % (long) (period * 1000)) / (period * 1000.0f);
        float alphaFactor = (float) Math.sin(phase * 2 * Math.PI);
        int dynamicAlpha = (int) ((alphaFactor + 1) / 2 * (maxAlpha - minAlpha) + minAlpha);
        float size = 0.4f;
        vertexBuilder.vertex(poseMatrix, -size, 0, -size)
                .color(255, 255, 255, dynamicAlpha)
                .uv(0, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, -size)
                .color(255, 255, 255, dynamicAlpha)
                .uv(1, 0)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, size, 0, size)
                .color(255, 255, 255, dynamicAlpha)
                .uv(1, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        vertexBuilder.vertex(poseMatrix, -size, 0, size)
                .color(255, 255, 255, dynamicAlpha)
                .uv(0, 1)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
        poseStack.popPose();
        buffer.endBatch();
    }

    public static void renderCompleteDynamicHaloHorizontal(PoseStack poseStack, Player player, float partialTick, ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);
        float yaw = player.getViewYRot(partialTick);
        Quaternionf rotation = new Quaternionf().rotateY(-yaw * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;
        float period = 3.0f;
        float minAlpha = 50.0f;
        float maxAlpha = 200.0f;
        float minBrightness = 0.5f;
        float maxBrightness = 1.0f;
        long currentTime = System.currentTimeMillis();
        float phase = (currentTime % (long) (period * 1000)) / (period * 1000.0f);
        float waveFactor = (float) Math.sin(phase * 2 * Math.PI);
        int dynamicAlpha = (int) ((waveFactor + 1) / 2 * (maxAlpha - minAlpha) + minAlpha);
        int dynamicBrightness = (int) (((waveFactor + 1) / 2 * (maxBrightness - minBrightness) + minBrightness) * 255);
        float size = 0.4f;
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, -size, 0, -size, 0, 0, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, size, 0, -size, 1, 0, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, size, 0, size, 1, 1, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, -size, 0, size, 0, 1, dynamicBrightness, dynamicAlpha, overlay, light);
        poseStack.popPose();
        buffer.endBatch();
    }

    public static void renderRotationHaloHorizontal(PoseStack poseStack, Player player, float partialTick, ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);
        float rotationAngle = player.tickCount * 18 + partialTick;
        Quaternionf rotation = new Quaternionf().rotateY(rotationAngle * (float) Math.PI / 180);
        poseStack.mulPose(rotation);
        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;
        int fixedAlpha = 255;
        int fixedBrightness = 255;
        float size = 0.4f;
        buildVertexWithoutBrightness(vertexBuilder, poseMatrix, normalMatrix, -size, 0, -size, 0, 0, fixedBrightness, fixedBrightness, fixedBrightness, fixedAlpha, overlay, light);
        buildVertexWithoutBrightness(vertexBuilder, poseMatrix, normalMatrix, size, 0, -size, 1, 0, fixedBrightness, fixedBrightness, fixedBrightness, fixedAlpha, overlay, light);
        buildVertexWithoutBrightness(vertexBuilder, poseMatrix, normalMatrix, size, 0, size, 1, 1, fixedBrightness, fixedBrightness, fixedBrightness, fixedAlpha, overlay, light);
        buildVertexWithoutBrightness(vertexBuilder, poseMatrix, normalMatrix, -size, 0, size, 0, 1, fixedBrightness, fixedBrightness, fixedBrightness, fixedAlpha, overlay, light);
        poseStack.popPose();
        buffer.endBatch();
    }

    private static void buildVertexWithoutBrightness(VertexConsumer consumer, Matrix4f poseMatrix, Matrix3f normalMatrix, float x, float y, float z, float u, float v, int red, int green, int blue, int alpha, int overlay, int light) {
        consumer.vertex(poseMatrix, x, y, z)
                .color(red, green, blue, alpha)
                .uv(u, v)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMatrix, 0, 1, 0)
                .endVertex();
    }

    private static void buildVertex(VertexConsumer builder, Matrix4f pose, Matrix3f normal, float x, float y, float z, float u, float v, int brightness, int alpha, int overlay, int light) {
        builder.vertex(pose, x, y, z)
                .color(brightness, brightness, brightness, alpha)
                .uv(u, v)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal, 0, 1, 0)
                .endVertex();
    }
}
