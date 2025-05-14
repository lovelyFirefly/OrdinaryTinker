package com.hoshino.ordinarytinker.Content.Client.Renderer.Layer;

import com.hoshino.ordinarytinker.Register.OrdinaryTinkerItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class SoulgeRendererLayer extends ItemInHandLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public SoulgeRendererLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer, pItemInHandRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.getMainHandItem().getItem() == OrdinaryTinkerItem.soulge.get()) {
            // 矩阵初始化
            pPoseStack.pushPose();
            try {
                // 获取第一人称视角矩阵
                HumanoidArm mainArm = pLivingEntity.getMainArm();
                boolean rightHand = (mainArm == HumanoidArm.RIGHT);
                this.getParentModel().translateToHand(rightHand ? HumanoidArm.RIGHT : HumanoidArm.LEFT, pPoseStack);

                // 基础物品渲染
                var renderer = Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer();
                renderer.renderItem(pLivingEntity, pLivingEntity.getMainHandItem(),
                        ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, false, pPoseStack, pBuffer, pPackedLight);

                // 动态参数计算
                long gameTime = pLivingEntity.level().getGameTime();
                float timeFactor = (gameTime % 80 + pPartialTicks) / 80.0f;
                float alpha = 0.4f + Mth.sin(timeFactor * Mth.PI) * 0.3f; // 波动透明度

                // 矩阵调整（适配第一人称视角）
                pPoseStack.translate(0, 0.1, 0.05); // 微调位置
                pPoseStack.scale(0.8f, 0.8f, 0.8f); // 适当缩小
                Matrix4f matrix = pPoseStack.last().pose();

                // 渲染状态配置
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                RenderSystem.depthMask(false);

                // 获取末地门专用渲染层
                VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.endPortal());

                // UV动态参数
                float uvSpeed = 0.75f; // 控制滚动速度
                float uvOffset = (gameTime % 240) * uvSpeed / 240.0f;

                // 四边形顶点定义（适配物品形状）
                float size = 0.5f;
                float depth = 0.001f;
                float uEnd = 1.0f + uvOffset;
                float vEnd = 1.0f + uvOffset;

                // 顶点颜色参数
                float r = 0.8f, g = 0.9f, b = 1.0f;
                // 构建四边形（使用三角形带顺序）
                vertexConsumer.vertex(matrix, -size, -size, depth)
                        .color(r, g, b, alpha)
                        .uv(uvOffset, uvOffset)
                        .endVertex();

                vertexConsumer.vertex(matrix, -size, size, depth)
                        .color(r, g, b, alpha)
                        .uv(uvOffset, vEnd)
                        .endVertex();

                vertexConsumer.vertex(matrix, size, -size, depth)
                        .color(r, g, b, alpha)
                        .uv(uEnd, uvOffset)
                        .endVertex();

                vertexConsumer.vertex(matrix, size, size, depth)
                        .color(r, g, b, alpha)
                        .uv(uEnd, vEnd)
                        .endVertex();
            } finally {
                // 状态恢复
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                pPoseStack.popPose();
            }
        }
    }
}
