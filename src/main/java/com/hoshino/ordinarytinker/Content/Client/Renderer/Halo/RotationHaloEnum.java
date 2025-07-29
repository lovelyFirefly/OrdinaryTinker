package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import com.hoshino.ordinarytinker.Content.Util.ModifierLevel;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.function.Supplier;

public enum RotationHaloEnum implements HaloRendererUtil {
    LXJ(OrdinaryTinkerModifier.chachaStaticModifier, "lxj");
    private final Supplier<Modifier> modifierSupplier;
    private final ResourceLocation texture;
    private Modifier cachedModifier;

    RotationHaloEnum(Supplier<Modifier> modifierSupplier, String textureName) {
        this.modifierSupplier = modifierSupplier;
        this.texture = OrdinaryTinker.getResource("textures/halo/" + textureName + ".png");
    }

    public Modifier getModifier() {
        if (cachedModifier == null) {
            cachedModifier = modifierSupplier.get();
        }
        return cachedModifier;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public boolean checkClientRenderCondition(Player player) {
        return ModifierLevel.getAllSlotModifierlevel(player, this.getModifierId()) > 0;
    }

    @Override
    public void doRender(PoseStack poseStack, Player player, float partialTick) {
        HaloRenderLogic.renderRotationHaloHorizontal(poseStack, player, partialTick, this.texture);
    }

    @Override
    public ModifierId getModifierId() {
        return getModifier().getId();
    }
}
