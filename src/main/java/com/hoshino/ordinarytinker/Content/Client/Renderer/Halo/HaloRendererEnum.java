package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import com.hoshino.ordinarytinker.Register.OrdinaryTinkerModifier;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public enum HaloRendererEnum implements HaloRendererUtil {
    HOSHINO(OrdinaryTinkerModifier.hoshinoHaloStaticModifier.get(), "hoshino"),
    AZUSA(OrdinaryTinkerModifier.azusaHaloStaticModifier.get(), "azusa"),
    REISA(OrdinaryTinkerModifier.reisaHaloStaticModifier.get(), "reisa"),
    NATSU(OrdinaryTinkerModifier.natsuHaloStaticModifier.get(), "natsu"),
    AL1S(OrdinaryTinkerModifier.al1sHaloStaticModifier.get(), "al1s"),
    MARI(OrdinaryTinkerModifier.mariHaloStaticModifier.get(), "mari"),
    LXJ(OrdinaryTinkerModifier.chachaStaticModifier.get(), "lxj");
    private final Modifier modifier;
    private final ResourceLocation texture;

    HaloRendererEnum(Modifier modifierSupplier, String textureName) {
        this.modifier = modifierSupplier;
        this.texture = OrdinaryTinker.getResource("textures/halo/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public ModifierId getModifierId() {
        return modifier.getId();
    }
}
