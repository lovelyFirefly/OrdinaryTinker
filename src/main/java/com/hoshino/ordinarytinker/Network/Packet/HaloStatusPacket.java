package com.hoshino.ordinarytinker.Network.Packet;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Handler.ClientHoshinoHaloHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

// 自定义网络数据包（服务端 -> 客户端）
public class HaloStatusPacket extends BasePacket{
    private final boolean hasHalo;
    public HaloStatusPacket(boolean hasHalo){
        this.hasHalo=hasHalo;
    }
    public HaloStatusPacket(FriendlyByteBuf buf){
        this.hasHalo=buf.readBoolean();
    }

    @Override
    public void ToByte(FriendlyByteBuf buf) {
         buf.writeBoolean(this.hasHalo);
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {
        ClientHoshinoHaloHandler.setHasHalo(hasHalo);
    }
}
