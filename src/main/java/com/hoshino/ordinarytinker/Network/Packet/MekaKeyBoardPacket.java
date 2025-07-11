package com.hoshino.ordinarytinker.Network.Packet;

import com.hoshino.ordinarytinker.Content.Item.Tool.tinkeritem.MekaTool;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MekaKeyBoardPacket extends BasePacket {

    private final int ToolLevel;

    public MekaKeyBoardPacket(int ToolLevel) {
        this.ToolLevel = ToolLevel;
    }

    public MekaKeyBoardPacket(FriendlyByteBuf buf) {
        ToolLevel = buf.readInt();
    }

    @Override
    public void ToByte(FriendlyByteBuf buf) {
        buf.writeInt(ToolLevel);
    }

    @Override
    public void PacketHandler(Supplier<NetworkEvent.Context> supplier, NetworkEvent.Context context, ServerPlayer player, ServerLevel level) {
        if (player.getMainHandItem().getItem() instanceof MekaTool mekaTool) {
            ItemStack stack = player.getMainHandItem();
            if (ToolLevel == 0) {
                mekaTool.setToolLevel(1, stack);
            } else if (ToolLevel == 1) {
                mekaTool.setToolLevel(2, stack);
            } else if (ToolLevel == 2) {
                mekaTool.setToolLevel(3, stack);
            } else if (ToolLevel == 3) {
                mekaTool.setToolLevel(0, stack);
            }
        }
    }
}
