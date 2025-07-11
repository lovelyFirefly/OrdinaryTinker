package com.hoshino.ordinarytinker.Network.Packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SoulGeAttackPacket extends BasePacket {
    public final UUID mobUUID;
    public final int targetedTimes;

    public SoulGeAttackPacket(UUID UUID, int targetedTimes) {
        this.mobUUID = UUID;
        this.targetedTimes = targetedTimes;
    }

    public SoulGeAttackPacket(FriendlyByteBuf buf) {
        mobUUID = buf.readUUID();
        targetedTimes = buf.readInt();
    }

    @Override
    public void ToByte(FriendlyByteBuf buf) {
        buf.writeUUID(mobUUID);
        buf.writeInt(targetedTimes);
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {
        var mobs = level.getEntities().get(mobUUID);
        if (mobs != null) {
            mobs.getPersistentData().putInt("targeted", targetedTimes);
        }
    }
}
