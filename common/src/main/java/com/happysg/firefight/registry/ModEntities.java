package com.happysg.firefight.registry;

import com.happysg.firefight.Constants;
import com.happysg.firefight.client.renderer.SimpleProjectileRenderer;
import com.happysg.firefight.platform.Services;
import com.happysg.firefight.projectile.ExtendedProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class ModEntities {

    public static Supplier<EntityType<ExtendedProjectile>> SIMPLE_PROJECTILE =
            Services.REGISTRATION.registerEntityType("simple_projectile", () -> EntityType.Builder.of(ExtendedProjectile::new, MobCategory.MISC)
                    .sized(.25f, .25f)
                    .updateInterval(1)
                    .clientTrackingRange(256)
                    .build("simple_projectile"));

    public static void register() {
        Constants.LOGGER.info("Registering Mod Entities");
    }


    public static void registerRenderers() {
        Services.REGISTRATION.registerEntityRenderer(SIMPLE_PROJECTILE, SimpleProjectileRenderer::new);
    }
}
