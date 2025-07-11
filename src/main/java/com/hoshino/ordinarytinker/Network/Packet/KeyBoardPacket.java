package com.hoshino.ordinarytinker.Network.Packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyBoardPacket extends BasePacket {
    public KeyBoardPacket() {
    }

    public KeyBoardPacket(FriendlyByteBuf buf) {
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {

    }
}
