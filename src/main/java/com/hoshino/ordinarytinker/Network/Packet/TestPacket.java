package com.hoshino.ordinarytinker.Network.Packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class TestPacket extends BasePacket{
    public final UUID playerUUID;
    public TestPacket(UUID UUID){
        this.playerUUID = UUID;
    }
    public TestPacket(FriendlyByteBuf buf){
        playerUUID =buf.readUUID();
    }
    @Override
    public void ToByte(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {
        var entity= level.getEntity(playerUUID);
        if(entity==null)return;
        Sheep sheep=new Sheep(EntityType.SHEEP,level);
        level.addFreshEntity(sheep);
        sheep.moveTo(entity.position());
    }
}
