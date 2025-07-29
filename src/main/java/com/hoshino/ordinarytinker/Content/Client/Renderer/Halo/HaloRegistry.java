package com.hoshino.ordinarytinker.Content.Client.Renderer.Halo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HaloRegistry {
    private static final Set<HaloRendererUtil> ALL_HALOS_MUTABLE = new HashSet<>();

    static {
        Collections.addAll(ALL_HALOS_MUTABLE, HaloRendererEnum.values());
        Collections.addAll(ALL_HALOS_MUTABLE, RotationHaloEnum.values());
    }

    public static void registerHalo(HaloRendererUtil... halos) {
        Collections.addAll(ALL_HALOS_MUTABLE, halos);
    }

    public static Set<HaloRendererUtil> getAllHalos() {
        return Collections.unmodifiableSet(ALL_HALOS_MUTABLE);
    }
}
