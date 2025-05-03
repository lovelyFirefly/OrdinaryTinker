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

    public static void renderHaloIncline(PoseStack poseStack, Player player, float partialTick,ResourceLocation haloTexture) {
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
    public static void renderHaloHorizontal(PoseStack poseStack, Player player, float partialTick,ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);

        //水平旋转
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

        // 顶点
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
    public static void renderDynamicHaloHorizontal(PoseStack poseStack, Player player, float partialTick,ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);

        // 水平旋转（保持原逻辑）
        float yaw = player.getViewYRot(partialTick);
        Quaternionf rotation = new Quaternionf().rotateY(-yaw * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);

        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;

        // 新增：周期性明暗参数
        float period = 3.0f; // 完整呼吸周期（秒）
        float minAlpha = 50.0f; // 最小透明度
        float maxAlpha = 200.0f; // 最大透明度

        // 计算动态透明度
        long currentTime = System.currentTimeMillis();
        float phase = (currentTime % (long)(period * 1000)) / (period * 1000.0f); // 0~1周期进度
        float alphaFactor = (float) Math.sin(phase * 2 * Math.PI); // 生成正弦波（-1~1）
        int dynamicAlpha = (int) ((alphaFactor + 1) / 2 * (maxAlpha - minAlpha) + minAlpha); // 映射到透明度区间

        float size = 0.4f;

        // 应用动态透明度到所有顶点
        vertexBuilder.vertex(poseMatrix, -size, 0, -size)
                .color(255, 255, 255, dynamicAlpha) // 动态透明度
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
    public static void renderCompleteDynamicHaloHorizontal(PoseStack poseStack, Player player, float partialTick,ResourceLocation haloTexture) {
        poseStack.pushPose();
        poseStack.translate(0, player.getBbHeight() + 0.35f, 0);

        // 水平旋转逻辑
        float yaw = player.getViewYRot(partialTick);
        Quaternionf rotation = new Quaternionf().rotateY(-yaw * ((float) Math.PI / 180F));
        poseStack.mulPose(rotation);

        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(haloTexture));
        Matrix4f poseMatrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        int light = 15728880;
        int overlay = OverlayTexture.NO_OVERLAY;

        float period = 3.0f;                // 呼吸周期（s）
        float minAlpha = 50.0f;             // 透明度下限
        float maxAlpha = 200.0f;            // 透明度上限
        float minBrightness = 0.5f;         // 亮度下限
        float maxBrightness = 1.0f;         // 亮度上限

        // 计算动态系数
        long currentTime = System.currentTimeMillis();
        float phase = (currentTime % (long)(period * 1000)) / (period * 1000.0f);
        float waveFactor = (float) Math.sin(phase * 2 * Math.PI);

        // 映射到实际范围
        int dynamicAlpha = (int) ((waveFactor + 1) / 2 * (maxAlpha - minAlpha) + minAlpha);
        int dynamicBrightness = (int) (
                ((waveFactor + 1) / 2 * (maxBrightness - minBrightness) + minBrightness) * 255
        );

        //顶点定义
        float size = 0.4f;
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, -size, 0, -size, 0, 0, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, size, 0, -size, 1, 0, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, size, 0, size, 1, 1, dynamicBrightness, dynamicAlpha, overlay, light);
        buildVertex(vertexBuilder, poseMatrix, normalMatrix, -size, 0, size, 0, 1, dynamicBrightness, dynamicAlpha, overlay, light);

        poseStack.popPose();
        buffer.endBatch();
    }

    // 封装顶点构建逻辑
    private static void buildVertex(
            VertexConsumer builder, Matrix4f pose, Matrix3f normal,
            float x, float y, float z, float u, float v,
            int brightness, int alpha, int overlay, int light
    ) {
        builder.vertex(pose, x, y, z)
                .color(brightness, brightness, brightness, alpha) // 动态亮度和透明度
                .uv(u, v)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal, 0, 1, 0)
                .endVertex();
    }
}
