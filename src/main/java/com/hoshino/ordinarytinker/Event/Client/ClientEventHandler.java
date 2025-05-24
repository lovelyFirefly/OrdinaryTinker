package com.hoshino.ordinarytinker.Event.Client;

import com.hoshino.ordinarytinker.Content.Client.KeyBroad.KeyBinding;

import com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem.MekaTool;
import com.hoshino.ordinarytinker.Network.OTChannel;
import com.hoshino.ordinarytinker.Network.Packet.MekaKeyBoardPacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
    }
    @SubscribeEvent
    public static void onKeyPressed(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if(player.level().isClientSide()){
                if (player.getMainHandItem().getItem() instanceof MekaTool mekaTool) {
                    ItemStack stack = player.getMainHandItem();
                    if (KeyBinding.DIGGING_SPEED_KEY.consumeClick()){
                        if (mekaTool.getToolLevel(stack) == 0) {
                            OTChannel.SendToServer(new MekaKeyBoardPacket(0));
                            player.sendSystemMessage(Component.literal("切换到中速模式"));
                        } else if (mekaTool.getToolLevel(stack) == 1) {
                            OTChannel.SendToServer(new MekaKeyBoardPacket(1));
                            player.sendSystemMessage(Component.literal("切换到高速模式"));
                        } else if (mekaTool.getToolLevel(stack) == 2) {
                            OTChannel.SendToServer(new MekaKeyBoardPacket(2));
                            player.sendSystemMessage(Component.literal("切换到极速模式"));
                            player.sendSystemMessage(Component.literal("此模式可能产生幽灵方块"));
                        } else if (mekaTool.getToolLevel(stack) == 3) {
                            OTChannel.SendToServer(new MekaKeyBoardPacket(3));
                            player.sendSystemMessage(Component.literal("切换到低速模式"));
                        }
                    }
                }
            }
        }
    }
}
