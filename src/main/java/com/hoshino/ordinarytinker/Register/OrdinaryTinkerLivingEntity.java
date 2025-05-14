package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Entity.FriendlyZombie;
import com.hoshino.ordinarytinker.Content.Entity.Hajimi;
import com.hoshino.ordinarytinker.Content.Entity.SpecialArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerLivingEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<Hajimi>> HAJIMI = ENTITIES.register("hajimi",
            () -> EntityType.Builder.of(Hajimi::new, MobCategory.MISC)
                    .sized(0.6F, 0.7F)
                    .build(new ResourceLocation(MODID, "hajimi").toString())
    );
    public static final RegistryObject<EntityType<FriendlyZombie>> FRIENDLYZOMBIE = ENTITIES.register("friendlyzombie",
            () -> EntityType.Builder.of(FriendlyZombie::new, MobCategory.MISC)
                    .sized(0.6F, 0.7F)
                    .build(new ResourceLocation(MODID, "friendlyzombie").toString())
    );
    public static final RegistryObject<EntityType<SpecialArrow>> special_arrow = ENTITIES.register(
            "special_arrow",
            () -> EntityType.Builder.<SpecialArrow>of(SpecialArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("special_arrow")
    );
    public static void register(IEventBus bus){
        ENTITIES.register(bus);
    }
}
