package com.hoshino.ordinarytinker.Network.Packet;

import com.hoshino.ordinarytinker.Content.Client.Renderer.Halo.HaloRendererUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.UUID;
import java.util.function.Supplier;

public class HaloUpdatePacket {
    private final UUID playerUUID;
    private final ModifierId modifierId;
    private final boolean isEnabled;

    public HaloUpdatePacket(UUID playerUUID, ModifierId modifierId, boolean isEnabled) {
        this.playerUUID = playerUUID;
        this.modifierId = modifierId;
        this.isEnabled = isEnabled;
    }

    public HaloUpdatePacket(FriendlyByteBuf buf) {
        this.playerUUID = buf.readUUID();
        this.modifierId = new ModifierId(buf.readResourceLocation());
        this.isEnabled = buf.readBoolean();
    }

    public void toByte(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        ResourceLocation location = new ResourceLocation(modifierId.getNamespace(), modifierId.getPath());
        buf.writeResourceLocation(location);
        buf.writeBoolean(isEnabled);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            HaloRendererUtil.setPlayerHaloState(playerUUID, modifierId, isEnabled);
        });
        context.setPacketHandled(true);
    }
}
