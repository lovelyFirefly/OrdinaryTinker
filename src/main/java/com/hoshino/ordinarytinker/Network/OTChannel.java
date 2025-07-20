package com.hoshino.ordinarytinker.Network;

import com.hoshino.ordinarytinker.Network.Packet.HaloUpdatePacket;
import com.hoshino.ordinarytinker.Network.Packet.KeyBoardPacket;
import com.hoshino.ordinarytinker.Network.Packet.MekaKeyBoardPacket;
import com.hoshino.ordinarytinker.Network.Packet.SoulGeAttackPacket;
import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class OTChannel {
    private static SimpleChannel INSTANCE;
    public static int packetID = 0;

    public static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(OrdinaryTinker.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;
        net.messageBuilder(KeyBoardPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(KeyBoardPacket::new)
                .encoder(KeyBoardPacket::toByte)
                .consumerMainThread(KeyBoardPacket::handle)
                .add();
        //mekatool
        net.messageBuilder(MekaKeyBoardPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MekaKeyBoardPacket::new)
                .encoder(MekaKeyBoardPacket::toByte)
                .consumerMainThread(MekaKeyBoardPacket::handle)
                .add();
        //魂戈
        net.messageBuilder(SoulGeAttackPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SoulGeAttackPacket::new)
                .encoder(SoulGeAttackPacket::toByte)
                .consumerMainThread(SoulGeAttackPacket::handle)
                .add();
        net.messageBuilder(HaloUpdatePacket.class,id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(HaloUpdatePacket::new)
                .encoder(HaloUpdatePacket::toByte)
                .consumerMainThread(HaloUpdatePacket::handle)
                .add();
    }

    public static <MSG> void SendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void SendToPlayer(MSG msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static <MSG> void sendToClient(MSG msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
    public static <MSG> void sendToTrackingAndSelf(MSG msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), msg);
    }
}