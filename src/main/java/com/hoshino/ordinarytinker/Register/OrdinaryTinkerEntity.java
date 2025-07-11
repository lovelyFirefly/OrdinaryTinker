package com.hoshino.ordinarytinker.Register;

import com.hoshino.ordinarytinker.Content.Entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hoshino.ordinarytinker.OrdinaryTinker.MODID;

public class OrdinaryTinkerEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<Hajimi>> HAJIMI = ENTITIES.register("hajimi",
            () -> EntityType.Builder.of(Hajimi::new, MobCategory.MISC)
                    .sized(0.6F, 0.7F)
                    .build(new ResourceLocation(MODID, "hajimi").toString())
    );
    public static final RegistryObject<EntityType<SpecialArrow>> special_arrow = ENTITIES.register(
            "special_arrow",
            () -> EntityType.Builder.<SpecialArrow>of(SpecialArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("special_arrow")
    );
    public static final RegistryObject<EntityType<HugeArrow>> huge_arrow = ENTITIES.register(
            "huge_arrow",
            () -> EntityType.Builder.<HugeArrow>of(HugeArrow::new, MobCategory.MISC)
                    .sized(8, 8)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("huge_arrow")
    );
    public static final RegistryObject<EntityType<FallenStar>> fallen_star = ENTITIES.register(
            "fallen_star",
            () -> EntityType.Builder.<FallenStar>of(FallenStar::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("fallen_star")
    );
    public static final RegistryObject<EntityType<EagleAmmo>> eagle_ammo = ENTITIES.register(
            "eagle_ammo",
            () -> EntityType.Builder.<EagleAmmo>of(EagleAmmo::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("eagle_ammo")
    );

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
