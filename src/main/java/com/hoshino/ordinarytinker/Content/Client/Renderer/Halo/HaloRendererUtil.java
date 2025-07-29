package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public interface HaloRendererUtil {

    ModifierId getModifierId();

    ResourceLocation getTexture();

    boolean checkClientRenderCondition(Player player);

    void doRender(PoseStack poseStack, Player player, float partialTick);
}
