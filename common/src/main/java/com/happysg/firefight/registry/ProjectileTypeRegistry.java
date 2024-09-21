package com.happysg.firefight.registry;

import com.happysg.firefight.projectile.ProjectileType;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectileTypeRegistry {

    private static final Map<ResourceLocation, ProjectileType> PROJECTILE_TYPE_REGISTRY = new HashMap<>();

    public static void register(ProjectileType projectileType) {
        if (projectileType == null) {
            throw new IllegalArgumentException("Projectile type cannot be null");
        }
        if (PROJECTILE_TYPE_REGISTRY.containsKey(projectileType.id())) {
            throw new IllegalArgumentException("Duplicate projectile type id: " + projectileType.id());
        }
        PROJECTILE_TYPE_REGISTRY.put(projectileType.id(), projectileType);
    }

    public static ProjectileType get(ResourceLocation id) {
        return PROJECTILE_TYPE_REGISTRY.get(id);
    }

    public static List<ProjectileType> getAll() {
        return List.copyOf(PROJECTILE_TYPE_REGISTRY.values());
    }

}