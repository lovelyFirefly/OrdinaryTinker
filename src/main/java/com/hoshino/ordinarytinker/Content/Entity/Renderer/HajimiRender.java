package com.hoshino.ordinarytinker.Content.Entity.Renderer;

import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import org.jetbrains.annotations.NotNull;

public class HajimiRender extends CatRenderer { // ✅ 继承原版猫的渲染器
    public HajimiRender(EntityRendererProvider.Context context) {
        super(context); // 直接传递上下文，复用原版模型和贴图
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(Cat pEntity) {
        return new ResourceLocation("textures/entity/cat/tabby.png");
    }
}
