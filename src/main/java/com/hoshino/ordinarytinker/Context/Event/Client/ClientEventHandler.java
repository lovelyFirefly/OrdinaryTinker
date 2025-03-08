package com.hoshino.ordinarytinker.Context.Event.Client;

import com.hoshino.ordinarytinker.Context.Client.KeyBroad.KeyBinding;
import com.hoshino.ordinarytinker.Context.Network.OTChannel;
import com.hoshino.ordinarytinker.Context.Network.Packet.KeyBoardPacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OrdinaryTinker.MODID,value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    static void clientSetupEvent(FMLClientSetupEvent event) {

    }
    @SubscribeEvent
    public static void onKeyPressed(InputEvent.Key event){
        Player player=Minecraft.getInstance().player;
        if (player!= null) {
                if(KeyBinding.DIGGING_SPEED_KEY.isDown()){
                    OTChannel.SendToServer(new KeyBoardPacket());
                }
        }
    }
}
