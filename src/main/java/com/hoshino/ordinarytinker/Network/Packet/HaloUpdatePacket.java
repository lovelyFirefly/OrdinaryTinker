package com.hoshino.ordinarytinker.Network.Packet;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloClientCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class HaloUpdatePacket {
    private final UUID playerUUID;
    private final Map<ModifierId, Boolean> haloStates;

    public HaloUpdatePacket(UUID playerUUID, Map<ModifierId, Boolean> haloStates) {
        this.playerUUID = playerUUID;
        this.haloStates = haloStates;
    }

    public HaloUpdatePacket(FriendlyByteBuf buf) {
        this.playerUUID = buf.readUUID();
        int size = buf.readVarInt();
        this.haloStates = new ConcurrentHashMap<>(size);
        for (int i = 0; i < size; i++) {
            ModifierId modifierId = new ModifierId(buf.readResourceLocation());
            boolean isEnabled = buf.readBoolean();
            this.haloStates.put(modifierId, isEnabled);
        }
    }

    public void toByte(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        buf.writeVarInt(haloStates.size());
        for (Map.Entry<ModifierId, Boolean> entry : haloStates.entrySet()) {
            var resourceLocation = new ResourceLocation(entry.getKey().getNamespace(), entry.getKey().getPath());
            buf.writeResourceLocation(resourceLocation);
            buf.writeBoolean(entry.getValue());
        }
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            HaloClientCache.updatePlayerHaloStates(playerUUID, haloStates);
        });
        context.setPacketHandled(true);
    }
}
