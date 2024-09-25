package com.happysg.firefight.platform;

import com.happysg.firefight.Constants;
import com.happysg.firefight.platform.services.IRegistrationHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FabricRegistrationHelper implements IRegistrationHelper {

    @Override
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String name, Supplier<T> supplier) {
        T entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, Constants.rl(name), supplier.get());
        return () -> entityType;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> supplier) {
        T item = Registry.register(BuiltInRegistries.ITEM, Constants.rl(name), supplier.get());
        return () -> item;
    }

    @Override
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityType, EntityRendererProvider<T> renderer) {
        EntityRendererRegistry.register(entityType.get(), renderer);
    }

    public static void register() {
    }
}
