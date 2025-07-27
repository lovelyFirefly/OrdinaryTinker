package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OrdinaryTinkerBlock {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OrdinaryTinker.MODID);
//    public static final RegistryObject<Block> TCONKINETIC_BLOCK = BLOCKS.register("tconkinetic_block", () -> new TconKineticBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static void register(IEventBus bus){
        bus.register(BLOCKS);
    }
}
