package com.hoshino.ordinarytinker.Network.Packet;

import com.hoshino.ordinarytinker.Content.Util.ChangeField;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.function.Supplier;

public class ToolUpdatePacket extends BasePacket{
    private final int ToolLevel;

    public ToolUpdatePacket(int ToolLevel) {
        this.ToolLevel = ToolLevel;
    }

    public ToolUpdatePacket(FriendlyByteBuf buf) {
        ToolLevel = buf.readInt();
    }

    @Override
    public void ToByte(FriendlyByteBuf buf) {
        buf.writeInt(ToolLevel);
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {
     var local=Minecraft.getInstance().player;
     if(local!=null){
         var tool= ToolStack.from(local.getMainHandItem());
         if(tool instanceof ChangeField field){
             field.ordinarytinker$ChangeDamageField(ToolLevel);
         }
     }
    }
}
