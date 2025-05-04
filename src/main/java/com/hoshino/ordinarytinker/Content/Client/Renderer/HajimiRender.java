package com.hoshino.ordinarytinker.Content.Client.Renderer;

import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import org.jetbrains.annotations.NotNull;

public class HajimiRender extends CatRenderer {
    public HajimiRender(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(Cat pEntity) {
        return new ResourceLocation("textures/entity/cat/tabby.png");
    }
}
