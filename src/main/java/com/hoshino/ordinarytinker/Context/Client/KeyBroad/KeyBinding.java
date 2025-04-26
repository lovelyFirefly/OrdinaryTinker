package com.hoshino.ordinarytinker.Context.Client.KeyBroad;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

//存储按键类，处理在Event下
public class KeyBinding {
    //按键种类
    public static final String KEY_CATEGORY_ORDINARYTINKER="key.category.ordinarytinker";
    //具体按键
    public static final String KEY_ADJUST_DIGGING_SPEED="key.ordinarytinker.digging_speed";

    public static final KeyMapping DIGGING_SPEED_KEY=new KeyMapping(KEY_CATEGORY_ORDINARYTINKER, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y,KEY_ADJUST_DIGGING_SPEED);
}
