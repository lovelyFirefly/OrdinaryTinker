package com.hoshino.ordinarytinker.Context.Network;

import com.hoshino.ordinarytinker.Context.Network.Packet.KeyBoardPacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class OTChannel {
    private static SimpleChannel INSTANCE;
    public static int packetID=0;
    public static int id(){return packetID++;}

    public static void register(){
        SimpleChannel net= NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(OrdinaryTinker.MODID,"messages"))
                .networkProtocolVersion(()->"1.0")
                .clientAcceptedVersions(s->true)
                .serverAcceptedVersions(s->true)
                .simpleChannel();

        INSTANCE=net;

        net.messageBuilder(KeyBoardPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(KeyBoardPacket::new)
                .encoder(KeyBoardPacket::ToByte)
                .consumerMainThread(KeyBoardPacket::handle)
                .add();
    }
    public static <MSG> void SendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }
    public static <MSG> void SendToPlayer(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player),msg);
    }
}
