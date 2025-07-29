package com.hoshino.ordinarytinker.Register;


import com.hoshino.ordinarytinker.OrdinaryTinker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OrdinaryTinkerBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OrdinaryTinker.MODID);

//    public static final RegistryObject<BlockEntityType<TconKineticBlockEntity>> COUNTER_ENTITY = BLOCK_ENTITIES.register("tconkinetic_block_entity", () -> BlockEntityType.Builder.of(
//                    TconKineticBlockEntity::new,
//                    OrdinaryTinkerBlock.TCONKINETIC_BLOCK.get())
//            .build(DSL.emptyPartType()));

    public static void register(IEventBus bus) {
        bus.register(BLOCK_ENTITIES);
    }
}
