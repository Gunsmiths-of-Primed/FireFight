package com.happysg.firefight.registry;

import com.happysg.firefight.gun.GunType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GunTypeRegistry {
    private static final Map<ResourceLocation, GunType> GUN_TYPE_REGISTRY = new HashMap<>();


    public static void register(GunType gunType) {
        if (gunType == null) {
            throw new IllegalArgumentException("Gun type cannot be null");
        }
        if (GUN_TYPE_REGISTRY.containsKey(gunType.id())) {
            throw new IllegalArgumentException("Duplicate gun type id: " + gunType.id());
        }
        GUN_TYPE_REGISTRY.put(gunType.id(), gunType);
    }

    public static GunType get(ResourceLocation id) {
        return GUN_TYPE_REGISTRY.get(id);
    }

    public static List<GunType> getAll() {
        return List.copyOf(GUN_TYPE_REGISTRY.values());
    }

}
