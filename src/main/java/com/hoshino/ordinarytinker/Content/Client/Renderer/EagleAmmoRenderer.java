package com.hoshino.ordinarytinker.Content.Client.Renderer;

import com.hoshino.ordinarytinker.Content.Entity.EagleAmmo;
import com.hoshino.ordinarytinker.Content.Entity.FallenStar;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;

public abstract class EagleAmmoRenderer extends EntityRenderer<EagleAmmo> {

    private final ItemRenderer itemRenderer;

    protected EagleAmmoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(EagleAmmo pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(pEntity) < 12.25D)) {
            pPoseStack.pushPose();
            float scale = 5;
            pPoseStack.scale(scale, scale, scale);
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            this.itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.level(), pEntity.getId());
            pPoseStack.popPose();
            super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        }
    }
}
